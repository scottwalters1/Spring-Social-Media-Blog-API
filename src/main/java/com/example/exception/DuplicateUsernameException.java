package com.example.exception;

/**
 * Custom exception to represent an error where a duplicate username is found.
 */
public class DuplicateUsernameException extends RuntimeException {

    /**
     * Constructor to create a DuplicateUsernameException with a custom message.
     * 
     * @param message the detail message describing the reason for the exception.
     */
    public DuplicateUsernameException(String message) {
        super(message);
    }
}