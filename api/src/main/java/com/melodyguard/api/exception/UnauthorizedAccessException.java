package com.melodyguard.api.exception;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String msg){
        super(msg);
    }
}