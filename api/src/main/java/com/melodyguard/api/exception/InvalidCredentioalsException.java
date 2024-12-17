package com.melodyguard.api.exception;

public class InvalidCredentioalsException extends RuntimeException {
    public InvalidCredentioalsException(String msg){
        super(msg);
    }
}
