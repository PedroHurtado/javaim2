package com.example.demo.common.exceptions;

public class NotFoundException extends HttpException {

    public NotFoundException() {
        super(404);        
    }
    
}
