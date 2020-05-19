package com.github.varska.dictionary.service;

import com.github.varska.dictionary.entity.User;
import com.github.varska.dictionary.entity.Word;
import com.github.varska.dictionary.repository.WordRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WordServiceImpl implements WordService{

    private final WordRepo wordRepo;

    public WordServiceImpl(WordRepo wordRepo) {
        this.wordRepo = wordRepo;
    }

    @Override
    public void save(Word word) {
        word.setLocalDate(LocalDate.now());
        wordRepo.save(word);
    }

    @Override
    public List<Word> findAll() {
        return wordRepo.findAll();
    }

    @Override
    public List<Word> findByTitle(String title) {
        return wordRepo.findByTitle(title);
    }

    @Override
    public List<Word> findByUser(User user) {
        return wordRepo.findByUser(user);
    }
}
