package com.portifolyo.mesleki1.exceptions.apiexception;

public class DataIsExistsException extends RuntimeException {



    public DataIsExistsException() {
        super("Data Is Aldready Exists");
    }
}
