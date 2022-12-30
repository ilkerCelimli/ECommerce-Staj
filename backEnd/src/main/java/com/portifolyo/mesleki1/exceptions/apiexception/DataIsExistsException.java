package com.portifolyo.mesleki1.exceptions.apiexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


public class DataIsExistsException extends RuntimeException {


    public ResponseEntity<Object> DataIsExistException(Exception ex, WebRequest web) {
        return ResponseEntity.unprocessableEntity().build();
    }


}
