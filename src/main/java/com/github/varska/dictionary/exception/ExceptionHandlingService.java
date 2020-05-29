package com.github.varska.dictionary.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingService {

    @ExceptionHandler
    public String handleUsernameNotFound(UsernameNotFoundException e, Model model){
        model.addAttribute("exception", e.getMessage());

        return "errors/notfound";
    }

    @ExceptionHandler
    public String handleDefault(Exception e, Model model){
        model.addAttribute("exception", e.getMessage());
        return "errors/error";
    }
}
