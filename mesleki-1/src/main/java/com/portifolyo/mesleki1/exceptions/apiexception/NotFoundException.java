package com.portifolyo.mesleki1.exceptions.apiexception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundException extends RuntimeException {


    @ExceptionHandler
    public ResponseEntity NotFounExceptionHandler() throws NotFoundException{
        return ResponseEntity.noContent().build();
    }

}
