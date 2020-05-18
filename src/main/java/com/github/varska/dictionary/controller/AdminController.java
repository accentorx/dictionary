package com.github.varska.dictionary.controller;

import com.github.varska.dictionary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userlist")
    public String getUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "adminpage";
    }

    @PostMapping("/userlist")
    public String deleteUser(@RequestParam(required = true) Long id,
                             @RequestParam(required = true) String action,
                             Model model){
        if (action.equals("delete")){
            userService.deleteById(id);
        }

        return "redirect:/adminpage";
    }

    @GetMapping("/user{id}")
    public String getUser(@PathVariable("id") Long id,
                          Model model){
        model.addAttribute("users", userService.findById(id));
        return "adminpage";
    }
}
