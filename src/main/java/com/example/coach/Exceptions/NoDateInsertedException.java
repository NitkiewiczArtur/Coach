package com.example.coach.Exceptions;

public class NoDateInsertedException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "You've forgot about fullfiling date for Your workout result. Go back and fill it.";

    public NoDateInsertedException(){
        super(EXCEPTION_MESSAGE);
    }
}
