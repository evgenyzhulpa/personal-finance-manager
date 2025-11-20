package com.example.personal_finance_manager.repository;

import com.example.personal_finance_manager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Transaction entity data access operations.
 * Extends JpaRepository to provide standard CRUD operations.
 *
 * <p>This repository provides methods for accessing transaction data from the database.
 *
 * @author Personal Finance Manager Team
 * @version 1.0
 * @since 1.0
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
