package com.example.demo.common.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.common.exceptions.HttpException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(HttpException.class)
    ResponseEntity<?> notFound(HttpException ex){
        return ResponseEntity.status(ex.getCode()).body(null);
    }
}
