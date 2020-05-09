package com.github.varska.dictionary.controller;

import com.github.varska.dictionary.entity.Word;
import com.github.varska.dictionary.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class HomeController{

    private final WordService wordService;

    public HomeController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String getPosts(Model model) {
        model.addAttribute("words", wordService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String getAdd(){
        return "add";
    }

    @PostMapping("/add")
    public String addWord(@RequestParam String title,
                          @RequestParam String definition,
                          @RequestParam String example){

        Word word = new Word(title, definition, example);

        wordService.save(word);
        return "add";
    }
}