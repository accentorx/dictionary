package com.github.varska.dictionary.repository;

import com.github.varska.dictionary.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepo extends JpaRepository<Word, Long>{

    Word findByTitle(String title);
}
