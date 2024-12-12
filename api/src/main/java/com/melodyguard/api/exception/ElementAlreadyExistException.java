package com.melodyguard.api.exception;

public class ElementAlreadyExistException extends RuntimeException {
    public ElementAlreadyExistException(String entity, Long id){
        super(entity + (id != null ? " with id " + id : "") + " already exist.");
    }
}
