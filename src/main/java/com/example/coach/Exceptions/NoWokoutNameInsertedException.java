package com.example.coach.Exceptions;

public class NoWokoutNameInsertedException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "You've forgot about fullfiling workout name. Go back and fill it before You click \"+\".";

    public NoWokoutNameInsertedException(){
        super(EXCEPTION_MESSAGE);
    }
}
