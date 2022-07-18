package com.portifolyo.mesleki1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SqlExceptionCustom extends SQLException {

    public SqlExceptionCustom() {
    }

    @ExceptionHandler(SqlExceptionCustom.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity sqlException(Exception ex, WebRequest web) {
        return ResponseEntity.badRequest().body("Sql Exception");
    }

}
