package com.example.cash_register_service.controller.controller_advice;

import java.time.ZonedDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the entire Spring Boot application.
 *
 * This class handles exceptions thrown by any controller or service, and provides a consistent
 * structure for error responses. It improves client-side error handling and simplifies debugging.
 *
 * Using @ControllerAdvice allows this class to intercept exceptions across all controllers.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions caused by invalid arguments, such as validation failures or bad request inputs.
     *
     * param: ex the caught IllegalArgumentException
     * return: a standardized 400 Bad Request response
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * Catches all other unhandled exceptions to prevent application crashes or unstructured error responses.
     *
     * The stack trace is printed to server logs for easier debugging.
     *
     * param: ex the caught exception
     * return: a generic 500 Internal Server Error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception ex) {
        ex.printStackTrace(); // For server logs
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred");
    }

    /**
     * Builds a consistent response body for errors.
     *
     * param: status  the HTTP status to return
     * param: message the error message
     * return: a ResponseEntity with detailed error info
     */
    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", ZonedDateTime.now());
        error.put("status", status.value());
        error.put("error", status.getReasonPhrase());
        error.put("message", message);
        return new ResponseEntity<>(error, status);
    }
}
