package com.github.varska.dictionary.controller;

import com.github.varska.dictionary.entity.User;
import com.github.varska.dictionary.entity.Word;
import com.github.varska.dictionary.service.WordService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
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

    @GetMapping("/word/{word}")
    public String getUser(@PathVariable("word") String word, Model model){

        List<Word> wordFromDb = wordService.findByTitle(word);

        model.addAttribute("wordFromDb", wordFromDb);

        return "word";
    }
}
