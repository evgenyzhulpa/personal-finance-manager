package com.example.personal_finance_manager.service.impl;

import com.example.personal_finance_manager.dto.request.UpsertTransactionRequest;
import com.example.personal_finance_manager.dto.response.TransactionListResponse;
import com.example.personal_finance_manager.dto.response.TransactionResponse;
import com.example.personal_finance_manager.exception.EntityNotFoundException;
import com.example.personal_finance_manager.mapper.TransactionMapper;
import com.example.personal_finance_manager.model.Transaction;
import com.example.personal_finance_manager.repository.TransactionRepository;
import com.example.personal_finance_manager.service.TransactionService;
import com.example.personal_finance_manager.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

/**
 * Implementation of TransactionService interface.
 * Provides business logic for transaction management operations including CRUD operations.
 *
 * <p>This service handles transaction-related operations such as creating, reading, updating,
 * and deleting financial transactions.
 *
 * @author Evgeny Zhulpa
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    
    /**
     * Repository for transaction data access operations.
     */
    private final TransactionRepository repository;
    
    /**
     * Mapper for converting between Transaction entity and DTOs.
     */
    private final TransactionMapper transactionMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionListResponse findAll() {
        return transactionMapper.transactionListToTransactionListResponse(
                repository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionResponse findById(Long id) {
        Transaction transaction = getTransactionById(id);
        return transactionMapper.transactionToTransactionResponse(transaction);
    }

    /**
     * Retrieves a transaction entity by ID.
     *
     * @param id the unique identifier of the transaction
     * @return Transaction entity
     * @throws EntityNotFoundException if transaction with the specified id is not found
     */
    private Transaction getTransactionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Транзакция с id {0} не найдена!", id))
                );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionResponse save(UpsertTransactionRequest request) {
        Transaction newTransaction = transactionMapper.transactionRequestToTransaction(request);
        return transactionMapper.transactionToTransactionResponse(
                repository.save(newTransaction));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public TransactionResponse update(Long id, UpsertTransactionRequest request) {
        Transaction existedTransaction = getTransactionById(id);
        Transaction transaction = transactionMapper.transactionRequestToTransaction(request);
        BeanUtils.copyNonNullProperties(transaction, existedTransaction);
        return transactionMapper.transactionToTransactionResponse(
                repository.save(existedTransaction));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
