package com.example.personal_finance_manager.mapper;

import com.example.personal_finance_manager.dto.request.UpsertTransactionRequest;
import com.example.personal_finance_manager.dto.response.TransactionListResponse;
import com.example.personal_finance_manager.dto.response.TransactionResponse;
import com.example.personal_finance_manager.model.Transaction;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(TransactionMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    Transaction transactionRequestToTransaction(UpsertTransactionRequest request);
    @Mapping(source = "transactionId", target = "id")
    Transaction transactionRequestToTransaction(Long transactionId, UpsertTransactionRequest request);
    TransactionResponse transactionToTransactionResponse(Transaction transaction);
    List<TransactionResponse> transactionListToTransactionResponseList(List<Transaction> transactions);
    default TransactionListResponse transactionListToTransactionListResponse(
            List<Transaction> transactions) {
        TransactionListResponse transactionListResponse = new TransactionListResponse();
        transactionListResponse.setTransactions(transactionListToTransactionResponseList(transactions));
        return transactionListResponse;
    }
}
