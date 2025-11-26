package com.example.personal_finance_manager.repository;

import com.example.personal_finance_manager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for User entity data access operations.
 * Extends JpaRepository to provide standard CRUD operations and custom query methods.
 *
 * <p>This repository provides methods for accessing user data from the database,
 * including methods for loading users with their associated transactions.
 *
 * @author Evgeny Zhulpa
 * @version 1.0
 * @since 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by ID and eagerly loads associated transactions.
     * Uses EntityGraph to prevent N+1 query problem.
     *
     * @param id the unique identifier of the user
     * @return Optional containing User with transactions if found, empty otherwise
     */
    @EntityGraph(attributePaths = {"transactions"})
    Optional<User> findWithTransactionsById(Long id);
}
