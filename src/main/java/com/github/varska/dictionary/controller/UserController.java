package com.github.varska.dictionary.controller;

import com.github.varska.dictionary.entity.User;
import com.github.varska.dictionary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
