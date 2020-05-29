package com.github.varska.dictionary.controller;

import com.github.varska.dictionary.entity.Word;
import com.github.varska.dictionary.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController{

    private WordService wordService;

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

    @Autowired
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }
}