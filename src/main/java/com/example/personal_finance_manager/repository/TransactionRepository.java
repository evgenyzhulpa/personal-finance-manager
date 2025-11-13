package com.example.personal_finance_manager.repository;

import com.example.personal_finance_manager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
