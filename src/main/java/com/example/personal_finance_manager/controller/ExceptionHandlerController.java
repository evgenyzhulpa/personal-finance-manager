package com.example.personal_finance_manager.controller;

import com.example.personal_finance_manager.dto.response.ErrorResponse;
import com.example.personal_finance_manager.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for REST controllers.
 * Handles exceptions thrown by controllers and converts them to appropriate HTTP responses.
 *
 * <p>This controller advice provides centralized exception handling across all REST endpoints.
 *
 * @author Evgeny Zhulpa
 * @version 1.0
 * @since 1.0
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    /**
     * Handles EntityNotFoundException and returns HTTP 404 (NOT_FOUND) response.
     *
     * @param ex the EntityNotFoundException that was thrown
     * @return ResponseEntity containing ErrorResponse with error message
     *         and HTTP status 404 (NOT_FOUND)
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> notFound(EntityNotFoundException ex) {
        log.error("Error when trying to get an entity", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getLocalizedMessage()));
    }
}
