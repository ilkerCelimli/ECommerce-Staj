package com.portifolyo.mesleki1.exceptions.apiexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmailActiviteException extends RuntimeException {

    @ExceptionHandler
    public ResponseEntity userRegisterException() {
        return ResponseEntity.badRequest().body("Check Code Wrong");
    }

}
