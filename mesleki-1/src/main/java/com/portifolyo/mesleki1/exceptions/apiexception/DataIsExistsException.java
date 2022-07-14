package com.portifolyo.mesleki1.exceptions.apiexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.FOUND)
public class DataIsExistsException extends RuntimeException {


    @ExceptionHandler
    public ResponseEntity DataIsExistException() {
        return ResponseEntity.unprocessableEntity().build();
    }


}
