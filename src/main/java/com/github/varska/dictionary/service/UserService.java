package com.github.varska.dictionary.service;

import com.github.varska.dictionary.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    void save(User user);

    List<User> findAll();

    User findByUsername(String username);

    void deleteById(Long id);

    Optional<User> findById(Long id);

    User findByResetToken(String token);

    User findByEmail(String email);
}
