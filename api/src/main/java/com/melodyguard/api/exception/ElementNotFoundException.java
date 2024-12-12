package com.melodyguard.api.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String entity, String id){
        super(entity + (id != null ? " with id `" + id : "`") + " not found.");
    }
}
