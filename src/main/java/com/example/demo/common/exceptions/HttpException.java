package com.example.demo.common.exceptions;

public abstract class HttpException extends RuntimeException {
    private final Integer code;
    public HttpException(Integer code){
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
}
