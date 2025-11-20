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

/**
 * MapStruct mapper interface for converting between Transaction entity and DTOs.
 * Provides mapping methods for transaction-related data transformations.
 *
 * <p>This mapper handles conversions between:
 * <ul>
 *   <li>Transaction entity and TransactionResponse DTO</li>
 *   <li>UpsertTransactionRequest DTO and Transaction entity</li>
 *   <li>List of Transaction entities and TransactionListResponse DTO</li>
 * </ul>
 *
 * <p>Uses TransactionMapperDelegate for custom mapping logic, particularly
 * for resolving User entity from userId in request DTOs.
 *
 * @author Personal Finance Manager Team
 * @version 1.0
 * @since 1.0
 */
@DecoratedWith(TransactionMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    
    /**
     * Maps UpsertTransactionRequest DTO to Transaction entity.
     * Uses delegate to resolve User entity from userId.
     *
     * @param request the request DTO containing transaction data
     * @return Transaction entity
     */
    Transaction transactionRequestToTransaction(UpsertTransactionRequest request);
    
    /**
     * Maps UpsertTransactionRequest DTO to Transaction entity with specified ID.
     *
     * @param transactionId the transaction ID to set
     * @param request the request DTO containing transaction data
     * @return Transaction entity with specified ID
     */
    @Mapping(source = "transactionId", target = "id")
    Transaction transactionRequestToTransaction(Long transactionId, UpsertTransactionRequest request);
    
    /**
     * Maps Transaction entity to TransactionResponse DTO.
     *
     * @param transaction the Transaction entity to map
     * @return TransactionResponse DTO
     */
    TransactionResponse transactionToTransactionResponse(Transaction transaction);
    
    /**
     * Maps list of Transaction entities to list of TransactionResponse DTOs.
     *
     * @param transactions the list of Transaction entities to map
     * @return list of TransactionResponse DTOs
     */
    List<TransactionResponse> transactionListToTransactionResponseList(List<Transaction> transactions);
    
    /**
     * Maps list of Transaction entities to TransactionListResponse DTO.
     *
     * @param transactions the list of Transaction entities to map
     * @return TransactionListResponse DTO containing list of TransactionResponse DTOs
     */
    default TransactionListResponse transactionListToTransactionListResponse(
            List<Transaction> transactions) {
        TransactionListResponse transactionListResponse = new TransactionListResponse();
        transactionListResponse.setTransactions(transactionListToTransactionResponseList(transactions));
        return transactionListResponse;
    }
}
