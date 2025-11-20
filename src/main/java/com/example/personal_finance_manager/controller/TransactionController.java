package com.example.personal_finance_manager.controller;

import com.example.personal_finance_manager.dto.request.UpsertTransactionRequest;
import com.example.personal_finance_manager.dto.response.TransactionListResponse;
import com.example.personal_finance_manager.dto.response.TransactionResponse;
import com.example.personal_finance_manager.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing financial transactions in the system.
 * Provides CRUD operations for transaction management through REST API endpoints.
 *
 * <p>All endpoints are available under the base path {@code /api/transactions}.
 *
 * @author Personal Finance Manager Team
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    
    /**
     * Service for transaction-related business logic operations.
     */
    private final TransactionService transactionService;

    /**
     * Retrieves all transactions from the system.
     *
     * @return ResponseEntity containing TransactionListResponse with list of all transactions
     *         and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<TransactionListResponse> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    /**
     * Retrieves a transaction by its unique identifier.
     *
     * @param id the unique identifier of the transaction to retrieve
     * @return ResponseEntity containing TransactionResponse with transaction details
     *         and HTTP status 200 (OK)
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if transaction with the specified id is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    /**
     * Creates a new transaction in the system.
     *
     * @param request the request DTO containing transaction data to create
     * @return ResponseEntity containing TransactionResponse with created transaction details
     *         and HTTP status 201 (CREATED)
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if user specified in request is not found
     */
    @PostMapping
    public ResponseEntity<TransactionResponse> save(@RequestBody UpsertTransactionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.save(request));
    }

    /**
     * Updates an existing transaction by its unique identifier.
     *
     * @param id the unique identifier of the transaction to update
     * @param request the request DTO containing updated transaction data
     * @return ResponseEntity containing TransactionResponse with updated transaction details
     *         and HTTP status 200 (OK)
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if transaction with the specified id is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> update(@PathVariable Long id,
                                                      @RequestBody UpsertTransactionRequest request) {
        return ResponseEntity.ok(transactionService.update(id, request));
    }

    /**
     * Deletes a transaction by its unique identifier.
     *
     * @param id the unique identifier of the transaction to delete
     * @return ResponseEntity with no content and HTTP status 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
