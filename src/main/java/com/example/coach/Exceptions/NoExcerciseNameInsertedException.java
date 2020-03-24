package com.example.coach.Exceptions;

public class NoExcerciseNameInsertedException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "You've forgot about fullfiling excercise name. Go back and fill it before You add Excercise.";

    public NoExcerciseNameInsertedException(){
        super(EXCEPTION_MESSAGE);
    }
}
