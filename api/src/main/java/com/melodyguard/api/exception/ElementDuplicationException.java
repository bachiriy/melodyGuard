package com.melodyguard.api.exception;

public class ElementDuplicationException extends RuntimeException {
    public ElementDuplicationException(){
        super("Element duplication, this element already exists.");
    }
}
