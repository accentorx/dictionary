package com.github.varska.dictionary.controller;

import com.github.varska.dictionary.entity.User;
import com.github.varska.dictionary.entity.Word;
import com.github.varska.dictionary.service.UserService;
import com.github.varska.dictionary.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    private final WordService wordService;

    public UserController(UserService userService, WordService wordService) {
        this.userService = userService;
        this.wordService = wordService;
    }

    @GetMapping("/user/{username}")
    public String getUser(@PathVariable ("username") String username, Model model){

        User user = userService.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("Такого пользователя не существует");
        }

        List<Word> wordList = wordService.findByUser(user);
        model.addAttribute("userWords", wordList);

        return "profile";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model){

        model.addAttribute("userForm", new User());

        return "registration";
    }
    @PostMapping("/registration")
    public String addUSer(@ModelAttribute("userForm") @Valid User userForm,
                          BindingResult bindingResult,
                          Model model){

        User user = userService.findByUsername(userForm.getUsername());
        if (bindingResult.hasErrors()){
            return "registration";
        }

        if (!userForm.getPassword().equals(userForm.getConfirmPassword())){
            model.addAttribute("passError", "Пароли должны совпадать");
            return "registration";
        }

        if (user != null){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        userService.save(userForm);

        return "redirect:/";
    }


}
