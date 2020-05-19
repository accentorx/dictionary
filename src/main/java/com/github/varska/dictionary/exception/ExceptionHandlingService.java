package com.github.varska.dictionary.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingService {

    @ExceptionHandler
    public String handleDefault(Exception e){
        return "error";
    }
}
