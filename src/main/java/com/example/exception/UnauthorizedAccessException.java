package com.example.exception;

/**
 * Custom exception to represent an unauthorized access error.
 */
public class UnauthorizedAccessException extends RuntimeException {
    /**
     * Constructs a new UnauthorizedAccessException with the specified detail message.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}