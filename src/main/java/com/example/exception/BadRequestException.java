package com.example.exception;

/**
 * Custom exception to represent bad request errors.
 */
public class BadRequestException extends RuntimeException {

    /**
     * Constructor to create a BadRequestException with a custom message.
     * 
     * @param message the detail message describing the reason for the exception.
     */
    public BadRequestException(String message) {
        super(message);
    }
}