package com.github.varska.dictionary.rest;

import com.github.varska.dictionary.entity.Word;
import com.github.varska.dictionary.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DictionaryRestController {

    private WordService wordService;

    @GetMapping("/word/all")
    public List<Word> getAll(){
        return wordService.findAll();
    }

    @GetMapping("/word/{title}")
    public List<Word> searchWord(@PathVariable String title){
        return wordService.findByTitle(title);
    }


    @Autowired
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }
}
