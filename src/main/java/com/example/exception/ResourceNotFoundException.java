package com.example.exception;

/**
 * Custom exception to represent a resource not found error.
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
