package com.transaction.transaction.exceptions;

public class APIExceptions   extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public APIExceptions(){}

    public APIExceptions(String message){
        super(message);
    }
}
