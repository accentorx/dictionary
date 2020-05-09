package com.github.varska.dictionary.service;

import com.github.varska.dictionary.entity.Word;

import java.util.List;

public interface WordService {

    void save(Word word);

    List<Word> findAll();

    Word findByTitle(String title);
}
