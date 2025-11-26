package com.example.personal_finance_manager.mapper;

import com.example.personal_finance_manager.dto.request.UpsertTransactionRequest;
import com.example.personal_finance_manager.model.Transaction;
import com.example.personal_finance_manager.utils.UserEntityHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Delegate class for TransactionMapper providing custom mapping logic.
 * Handles complex mapping scenarios that require additional business logic,
 * such as resolving User entity from userId in request DTOs.
 *
 * <p>This delegate is used by MapStruct to implement custom mapping methods
 * that cannot be automatically generated.
 *
 * @author Evgeny Zhulpa
 * @version 1.0
 * @since 1.0
 */
public abstract class TransactionMapperDelegate implements TransactionMapper {

    /**
     * Helper utility for retrieving User entities.
     */
    @Autowired
    private UserEntityHelper entityHelper;

    /**
     * Maps UpsertTransactionRequest DTO to Transaction entity.
     * Resolves User entity from userId in the request using UserEntityHelper.
     *
     * @param request the request DTO containing transaction data
     * @return Transaction entity with resolved User
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if user with the specified userId is not found
     */
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

    /**
     * Maps UpsertTransactionRequest DTO to Transaction entity with specified ID.
     *
     * @param transactionId the transaction ID to set
     * @param request the request DTO containing transaction data
     * @return Transaction entity with specified ID and resolved User
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if user with the specified userId is not found
     */
    @Override
    public Transaction transactionRequestToTransaction(Long transactionId, UpsertTransactionRequest request) {
        Transaction transaction = transactionRequestToTransaction(request);
        transaction.setId(transactionId);
        return transaction;
    }
}
