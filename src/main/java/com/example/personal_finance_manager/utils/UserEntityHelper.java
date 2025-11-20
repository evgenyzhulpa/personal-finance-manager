package com.example.personal_finance_manager.utils;

import com.example.personal_finance_manager.exception.EntityNotFoundException;
import com.example.personal_finance_manager.model.User;
import com.example.personal_finance_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * Helper utility component for User entity operations.
 * Provides reusable methods for retrieving User entities with optional transaction loading.
 *
 * <p>This helper encapsulates common user retrieval logic and exception handling,
 * preventing code duplication across the application.
 *
 * @author Personal Finance Manager Team
 * @version 1.0
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class UserEntityHelper {

    /**
     * Repository for user data access operations.
     */
    private final UserRepository repository;

    /**
     * Retrieves a User entity by ID, optionally loading associated transactions.
     *
     * @param id the unique identifier of the user
     * @param withTransactions if true, eagerly loads user with transactions using EntityGraph;
     *                         if false, loads user without transactions
     * @return User entity
     * @throws EntityNotFoundException if user with the specified id is not found
     */
    public final User getUserById(Long id, boolean withTransactions) {
        Optional<User> userOptional = withTransactions ?
                repository.findWithTransactionsById(id) : repository.findById(id);
        return userOptional
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("User with id {0} is not found!", id)));
    }
}
