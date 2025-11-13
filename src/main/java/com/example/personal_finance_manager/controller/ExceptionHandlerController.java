package com.example.personal_finance_manager.controller;

import com.example.personal_finance_manager.dto.response.ErrorResponse;
import com.example.personal_finance_manager.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> notFound(EntityNotFoundException ex) {
        log.error("Error when trying to get an entity", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getLocalizedMessage()));
    }
}
