package com.portifolyo.mesleki1.exceptions.apiexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserRegisterException extends RuntimeException {

    @ExceptionHandler(UserRegisterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> userRegisterException(Exception ex, WebRequest web) {
        return ResponseEntity.badRequest().body("User already exists");
    }

}
