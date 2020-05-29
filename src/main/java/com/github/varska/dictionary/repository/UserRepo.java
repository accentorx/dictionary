package com.github.varska.dictionary.repository;

import com.github.varska.dictionary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByResetToken(String token);

    User findByEmail(String email);
}
