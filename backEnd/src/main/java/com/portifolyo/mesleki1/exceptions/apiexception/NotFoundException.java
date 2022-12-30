package com.portifolyo.mesleki1.exceptions.apiexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

public class NotFoundException extends RuntimeException {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> NotFoundExceptionHandler(Exception ex, WebRequest web) throws NotFoundException{
        return ResponseEntity.noContent().build();
    }

}
