package com.portifolyo.mesleki1.exceptions.apiexception;


public class EmailActiviteException extends RuntimeException {

        public EmailActiviteException() {
            super("Email is not active");
        }

}
