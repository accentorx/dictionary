package com.github.varska.dictionary.controller;

import com.github.varska.dictionary.entity.User;
import com.github.varska.dictionary.entity.Word;
import com.github.varska.dictionary.service.UserService;
import com.github.varska.dictionary.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private WordService wordService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setWordService(WordService wordService) {
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

        return "user/author";
    }
}
