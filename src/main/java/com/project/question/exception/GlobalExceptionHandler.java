package com.project.question.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> globalExceptionHandler(RuntimeException e) {
        return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
