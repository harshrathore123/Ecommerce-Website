package com.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> emailAlreadyExists(EmailAlreadyExistsException emailAlreadyExistsException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(emailAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> invalidCredential(InvalidCredentialsException invalidCredentialsException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(invalidCredentialsException.getMessage());
    }

}
