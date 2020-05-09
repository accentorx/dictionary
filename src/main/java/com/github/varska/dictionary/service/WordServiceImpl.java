package com.github.varska.dictionary.service;

import com.github.varska.dictionary.entity.Word;
import com.github.varska.dictionary.repository.WordRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService{

    private final WordRepo wordRepo;

    public WordServiceImpl(WordRepo wordRepo) {
        this.wordRepo = wordRepo;
    }

    @Override
    public void save(Word word) {
        wordRepo.save(word);
    }

    @Override
    public List<Word> findAll() {
        return wordRepo.findAll();
    }

    @Override
    public Word findByTitle(String title) {
        return wordRepo.findByTitle(title);
    }
}
