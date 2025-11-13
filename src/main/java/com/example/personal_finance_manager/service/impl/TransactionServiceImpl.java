package com.example.personal_finance_manager.service.impl;

import com.example.personal_finance_manager.dto.request.UpsertTransactionRequest;
import com.example.personal_finance_manager.dto.response.TransactionListResponse;
import com.example.personal_finance_manager.dto.response.TransactionResponse;
import com.example.personal_finance_manager.mapper.TransactionMapper;
import com.example.personal_finance_manager.model.Transaction;
import com.example.personal_finance_manager.repository.TransactionRepository;
import com.example.personal_finance_manager.service.TransactionService;
import com.example.personal_finance_manager.utils.BeanUtils;
import com.example.personal_finance_manager.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionListResponse findAll() {
        return transactionMapper.transactionListToTransactionListResponse(
                repository.findAll());
    }

    @Override
    public TransactionResponse findById(Long id) {
        Transaction transaction = getTransactionById(id);
        return transactionMapper.transactionToTransactionResponse(transaction);
    }

    private Transaction getTransactionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Транзакция с id {0} не найдена!", id))
                );
    }

    @Override
    public TransactionResponse save(UpsertTransactionRequest request) {
        Transaction newTransaction = transactionMapper.transactionRequestToTransaction(request);
        return transactionMapper.transactionToTransactionResponse(
                repository.save(newTransaction));
    }

    @Override
    @Transactional
    public TransactionResponse update(Long id, UpsertTransactionRequest request) {
        Transaction existedTransaction = getTransactionById(id);
        Transaction transaction = transactionMapper.transactionRequestToTransaction(request);
        BeanUtils.copyNonNullProperties(transaction, existedTransaction);
        return transactionMapper.transactionToTransactionResponse(
                repository.save(existedTransaction));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
