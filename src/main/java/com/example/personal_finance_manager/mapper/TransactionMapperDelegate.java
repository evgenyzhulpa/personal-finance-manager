package com.example.personal_finance_manager.mapper;

import com.example.personal_finance_manager.dto.request.UpsertTransactionRequest;
import com.example.personal_finance_manager.model.Transaction;
import com.example.personal_finance_manager.utils.UserEntityHelper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class TransactionMapperDelegate implements TransactionMapper {

    @Autowired
    private UserEntityHelper entityHelper;

    @Override
    public Transaction transactionRequestToTransaction(UpsertTransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());
        transaction.setType(request.getType());
        transaction.setCategory(request.getCategory());
        transaction.setDescription(request.getDescription());
        transaction.setUser(entityHelper.getUserById(request.getUserId(), false));
        return transaction;
    }

    @Override
    public Transaction transactionRequestToTransaction(Long transactionId, UpsertTransactionRequest request) {
        Transaction transaction = transactionRequestToTransaction(request);
        transaction.setId(transactionId);
        return transaction;
    }
}
