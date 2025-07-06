package com.example.supermarket_service.controller.controller_advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.PersistenceException;

/**
 * Global exception handler for the entire microservice.
 *
 * This class uses RestControllerAdvice annotation to intercept exceptions thrown by any controller
 * and convert them into well-structured HTTP responses with meaningful status codes.
 *
 * Each method handles a specific category of exception, enabling centralized error handling.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles cases where an element is requested but not found.
     *
     * Example: Trying to access a resource from the database that doesn't exist,
     * or calling `.get()` on an empty Optional.
     *
     * Edge Case: No user found, no product found, missing record in DB
     *
     * Param: ex the thrown exception
     * RReturn: 404 Not Found with a message
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found: " + ex.getMessage());
    }

    /**
     * Handles database-related errors, such as query execution failures.
     *
     * Example: Issues with EntityManager, SQL syntax error, DB disconnect
     *
     * Edge Case: Native SQL query fails, malformed JSON_TABLE, DB unreachable
     *
     * Param ex the thrown exception
     * Return 500 Internal Server Error
     */
    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<String> handleDB(PersistenceException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred");
    }

    /**
     * Handles invalid arguments passed into methods.
     *
     * Example: Null or incorrect values passed into services or controllers
     *
     * Edge Case: Logic check fails, like empty user ID, or parsing issues
     *
     * Param: ex the thrown exception
     * Return: 400 Bad Request
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request: " + ex.getMessage());
    }

    /**
     * Catch-all handler for any unhandled exception in the application.
     *
     * Edge Case: NullPointerException, unexpected bugs, runtime failures
     *
     * Param: ex the thrown exception
     * Return: 500 Internal Server Error with generic message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + ex.getMessage());
    }
}
