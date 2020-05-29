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
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model){

        model.addAttribute("userForm", new User());

        return "user/registration";
    }
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm,
                          BindingResult bindingResult,
                          Model model){

        User user = userService.findByUsername(userForm.getUsername());
        if (bindingResult.hasErrors()){
            return "user/registration";
        }

        if (!userForm.getPassword().equals(userForm.getConfirmPassword())){
            model.addAttribute("passError", "Пароли должны совпадать");
            return "user/registration";
        }

        if (user != null){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "user/registration";
        }

        if (userService.findByEmail(userForm.getEmail()) != null){
            model.addAttribute("emailError", "Пользователь с такой почтой уже существует");
            return "user/registration";
        }

        userService.save(userForm);

        return "redirect:/";
    }
}
