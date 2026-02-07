package com.resort.management.exception;

public class badRequestException extends RuntimeException {
    public badRequestException(String message) {
        super(message);
    }
}
