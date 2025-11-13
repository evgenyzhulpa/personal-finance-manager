package com.example.personal_finance_manager.service;

import com.example.personal_finance_manager.dto.request.UpsertTransactionRequest;
import com.example.personal_finance_manager.dto.response.TransactionListResponse;
import com.example.personal_finance_manager.dto.response.TransactionResponse;

public interface TransactionService {
    TransactionListResponse findAll();
    TransactionResponse findById(Long id);
    TransactionResponse save(UpsertTransactionRequest request);
    TransactionResponse update(Long id, UpsertTransactionRequest request);
    void deleteById(Long id);
}
