package com.example.personal_finance_manager.service;

import com.example.personal_finance_manager.dto.request.UpsertTransactionRequest;
import com.example.personal_finance_manager.dto.response.TransactionListResponse;
import com.example.personal_finance_manager.dto.response.TransactionResponse;

/**
 * Service interface for transaction management operations.
 * Defines business logic methods for transaction-related operations.
 *
 * @author Evgeny Zhulpa
 * @version 1.0
 * @since 1.0
 */
public interface TransactionService {
    
    /**
     * Retrieves all transactions from the system.
     *
     * @return TransactionListResponse containing list of all transactions
     */
    TransactionListResponse findAll();
    
    /**
     * Retrieves a transaction by its unique identifier.
     *
     * @param id the unique identifier of the transaction
     * @return TransactionResponse containing transaction details
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if transaction with the specified id is not found
     */
    TransactionResponse findById(Long id);
    
    /**
     * Creates a new transaction in the system.
     *
     * @param request the request DTO containing transaction data to create
     * @return TransactionResponse containing created transaction details
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if user specified in request is not found
     */
    TransactionResponse save(UpsertTransactionRequest request);
    
    /**
     * Updates an existing transaction by its unique identifier.
     *
     * @param id the unique identifier of the transaction to update
     * @param request the request DTO containing updated transaction data
     * @return TransactionResponse containing updated transaction details
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if transaction with the specified id is not found
     */
    TransactionResponse update(Long id, UpsertTransactionRequest request);
    
    /**
     * Deletes a transaction by its unique identifier.
     *
     * @param id the unique identifier of the transaction to delete
     */
    void deleteById(Long id);
}
