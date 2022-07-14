package com.portifolyo.mesleki1.exceptions.apiexception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRegisterException extends RuntimeException {

    @ExceptionHandler
    public ResponseEntity userRegisterException() {
        return ResponseEntity.badRequest().body("User already exists");
    }

}
