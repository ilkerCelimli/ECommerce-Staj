package com.portifolyo.mesleki1.exceptions;

import com.portifolyo.mesleki1.exceptions.apiexception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GenericException {


    @ExceptionHandler(DataIsExistsException.class)
    public ResponseEntity<Object> dataIsExistException(Exception ex, WebRequest req) {
        return ResponseEntity.status(406).body(ex.getMessage());
    }

    @ExceptionHandler(EmailActiviteException.class)
    public ResponseEntity<Object> emailActiviteExeption(Exception ex,WebRequest req) {
        return ResponseEntity.status(403).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(Exception ex,WebRequest req) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(SqlExceptionCustom.class)
    public ResponseEntity<Object> SqlException(Exception ex,WebRequest req){
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(UserRegisterException.class)
    public ResponseEntity<Object> UserRegisterException(Exception ex,WebRequest req) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
