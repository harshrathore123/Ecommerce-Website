package com.orderservice.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

//    Custom Exception
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> orderNotFoundHanlder(
            OrderNotFoundException exception
    ){
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> productNotFoundHanlder(
            ProductNotFoundException exception
    ){
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundHanlder(
            UserNotFoundException exception
    ){
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

//    Validation Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(
            MethodArgumentNotValidException exception
    ){
        Map<String,String> error = new HashMap<>();
        for(FieldError field: exception.getBindingResult().getFieldErrors()){
            error.put(field.getField(),field.getDefaultMessage());
        }

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

//    Fiegn Exception
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(
            FeignException exception
    ){
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.valueOf(exception.status())
        );
    }

}
