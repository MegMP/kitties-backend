package com.spring.kitties.exception;

public class DuplicateUserFieldException extends RuntimeException {
    public DuplicateUserFieldException(String message) {
        super(message);
    }
}
