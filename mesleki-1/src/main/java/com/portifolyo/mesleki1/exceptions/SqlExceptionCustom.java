package com.portifolyo.mesleki1.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class SqlExceptionCustom extends SQLException {

    public SqlExceptionCustom() {
        super();
    }
    @ExceptionHandler
    public ResponseEntity sqlException() {
        return ResponseEntity.badRequest().body("Sql Exception");
    }

}
