package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller for handling exceptions.
 */
@RestControllerAdvice
public class ExceptionAndErrorController {

    /**
     * Handles {@link BadRequestException} and returns an HTTP 400 Bad Request
     * response
     * with the exception message.
     *
     * @param e the BadRequestException
     * @return the error message from the exception
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequestException(BadRequestException e) {
        return e.getMessage();
    }

    /**
     * Handles {@link UnauthorizedAccessException} and returns an HTTP 401
     * Unauthorized response
     * with the exception message.
     *
     * @param e the UnauthorizedAccessException
     * @return the error message from the exception
     */
    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorizedAccessException(UnauthorizedAccessException e) {
        return e.getMessage();
    }

    /**
     * Handles {@link ResourceNotFoundException} and returns an HTTP 400 Bad Request
     * response
     * with the exception message.
     *
     * @param e the ResourceNotFoundException
     * @return the error message from the exception
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleResourceNotFoundException(ResourceNotFoundException e) {
        return e.getMessage();
    }

    /**
     * Handles {@link DuplicateUsernameException} and returns an HTTP 409 Conflict
     * response
     * with the exception message.
     *
     * @param e the DuplicateUsernameException
     * @return the error message from the exception
     */
    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateUsernameException(DuplicateUsernameException e) {
        return e.getMessage();
    }

    /**
     * Handles {@link MissingServletRequestParameterException} and returns an HTTP
     * 400 Bad Request response
     * indicating which parameter is missing.
     *
     * @param e the MissingServletRequestParameterException
     * @return the name of the missing parameter with a message
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingParams(MissingServletRequestParameterException e) {
        return e.getParameterName() + " is missing in the query parameters and is required";
    }
}
