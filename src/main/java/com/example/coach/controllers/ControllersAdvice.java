package com.example.coach.controllers;

import com.example.coach.Exceptions.NoDateInsertedException;
import com.example.coach.Exceptions.NoWokoutNameInsertedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllersAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDateInsertedException.class)
    public ResponseEntity<Object> handleWebException(NoDateInsertedException e, WebRequest webRequest){
        log.error(e.getMessage());
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_ACCEPTABLE, webRequest);
    }
    @ExceptionHandler(NoWokoutNameInsertedException.class)
    public ResponseEntity<Object> handleWebException(NoWokoutNameInsertedException e, WebRequest webRequest){
        log.error(e.getMessage());
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_ACCEPTABLE, webRequest);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleWebException(Exception e, WebRequest webRequest){
        log.error(e.getMessage());
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
}
