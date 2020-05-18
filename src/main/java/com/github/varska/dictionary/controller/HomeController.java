package com.github.varska.dictionary.controller;

import com.github.varska.dictionary.entity.User;
import com.github.varska.dictionary.entity.Word;
import com.github.varska.dictionary.service.WordService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
class HomeController{

    private final WordService wordService;

    public HomeController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String getPosts(Model model, String title) {

        List<Word> words;

        if (title != null && !title.isEmpty()){
            words = wordService.findByTitle(title);
        }
        else {
            words = wordService.findAll();
        }

        model.addAttribute("words", words);

        return "index";
    }

    @GetMapping("/add")
    public String getAdd(){

        return "add";
    }

    @PostMapping("/add")
    public String addWord(@RequestParam String title,
                          @RequestParam String definition,
                          @RequestParam String example,
                          @AuthenticationPrincipal User user){

        Word word = new Word(title, definition, example, user);

        wordService.save(word);
        return "redirect:/";
    }
}