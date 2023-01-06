package com.portifolyo.mesleki1.exceptions;

import com.portifolyo.mesleki1.exceptions.apiexception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class GenericException {


    @ExceptionHandler(DataIsExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> dataIsExistException(Exception ex, WebRequest req) {
        log.error(String.format("%s, %s , %s",new Date(),ex.getMessage(),req.getContextPath()));
        return ResponseEntity.status(406).body(ex.getMessage());
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(EmailActiviteException.class)
    public ResponseEntity<Object> emailActiviteExeption(Exception ex,WebRequest req) {
        log.error(String.format("%s, %s , %s",new Date(),ex.getMessage(),req.getContextPath()));
        return ResponseEntity.status(403).body(ex.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(Exception ex,WebRequest req) {
        log.error(String.format("%s, %s , %s",new Date(),ex.getMessage(),req.getContextPath()));
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SqlExceptionCustom.class)
    public ResponseEntity<Object> SqlException(Exception ex,WebRequest req){
        log.error(String.format("%s, %s , %s",new Date(),ex.getMessage(),req.getContextPath()));
        return ResponseEntity.status(400).body(ex.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserRegisterException.class)
    public ResponseEntity<Object> UserRegisterException(Exception ex,WebRequest req) {
        log.error(String.format("%s, %s , %s",new Date(),ex.getMessage(),req.getContextPath()));
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
