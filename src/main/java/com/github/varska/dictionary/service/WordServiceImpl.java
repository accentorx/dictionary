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
        String title = word.getTitle().substring(0, 1).toUpperCase() + word.getTitle().substring(1);
        word.setTitle(title);
        wordRepo.save(word);
    }

    @Override
    public List<Word> findAll() {
        return wordRepo.findAllByOrderByIdDesc();
    }

    @Override
    public List<Word> findByTitle(String title) {
        List<Word> words = wordRepo.findByTitleIgnoreCase(title);
        if (words.isEmpty()){
            return wordRepo.findByTitleIgnoreCaseContaining(title);
        }
        return words;
    }

    @Override
    public List<Word> findByUser(User user) {
        return wordRepo.findByUser(user);
    }
}
