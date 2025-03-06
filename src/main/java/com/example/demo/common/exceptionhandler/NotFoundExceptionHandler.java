package com.example.demo.common.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.common.exceptions.NotFoundException;

@ControllerAdvice
public class NotFoundExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<?> notFound(NotFoundException ex){
        return ResponseEntity.status(404).body(null);
    }
}
