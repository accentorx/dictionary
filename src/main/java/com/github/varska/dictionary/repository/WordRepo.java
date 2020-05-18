package com.github.varska.dictionary.repository;

import com.github.varska.dictionary.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepo extends JpaRepository<Word, Long>{

    List<Word> findByTitle(String title);
}