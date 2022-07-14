package com.portifolyo.mesleki1.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class SqlExceptionCustom extends SQLException {

    public SqlExceptionCustom() {
        super();
    }
    @ExceptionHandler
    public ResponseEntity sqlException() {
        return ResponseEntity.badRequest().body("Sql Exception");
    }

}
