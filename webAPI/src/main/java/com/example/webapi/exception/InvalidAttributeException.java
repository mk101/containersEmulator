package com.example.webapi.exception;

public class InvalidAttributeException extends RuntimeException {
    public InvalidAttributeException(String message) {
        super(message);
    }
}
