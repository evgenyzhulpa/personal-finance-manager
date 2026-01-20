package com.example.personal_finance_manager.service;

import com.example.personal_finance_manager.dto.response.TransactionListResponse;
import com.example.personal_finance_manager.dto.response.TransactionResponse;
import com.example.personal_finance_manager.mapper.TransactionMapper;
import com.example.personal_finance_manager.model.Category;
import com.example.personal_finance_manager.model.Transaction;
import com.example.personal_finance_manager.model.TransactionType;
import com.example.personal_finance_manager.repository.TransactionRepository;
import com.example.personal_finance_manager.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository repository;
    @Mock
    private TransactionMapper transactionMapper;

    private TransactionServiceImpl transactionService;

    @BeforeEach
    public void setUp() {
        transactionService = new TransactionServiceImpl(repository, transactionMapper);
    }

    @Test
    @DisplayName("Given existing transactions, when findAll called, then return transactions")
    void givenExistingTransactions_whenFindAll_thenReturnTransactions() {
        List<Transaction> transactions = createTestTransactionList();
        TransactionListResponse expectedResponse = createTestTransactionListResponse(transactions);

        when(repository.findAll()).thenReturn(transactions);
        when(transactionMapper.transactionListToTransactionListResponse(transactions)).thenReturn(expectedResponse);

        TransactionListResponse actual = transactionService.findAll();

        assertThat(actual).isNotNull();
        assertThat(actual.getTransactions()).hasSize(transactions.size());
        assertThat(actual).isEqualTo(expectedResponse);

        verify(repository, times(1)).findAll();
        verify(transactionMapper, times(1)).transactionListToTransactionListResponse(transactions);
    }

    private List<Transaction> createTestTransactionList() {
        List<Transaction> transactions = List.of(
                createTestTransaction(1L, new BigDecimal(1000),
                        TransactionType.EXPENSE, Category.FOOD, "transaction 1"),
                createTestTransaction(2L, new BigDecimal(2000),
                        TransactionType.EXPENSE, Category.HEALTH, "transaction 2"),
                createTestTransaction(3L, new BigDecimal(3000),
                        TransactionType.INCOME, Category.SALARY, "transaction 3")
        );
        return transactions;
    }

    private Transaction createTestTransaction(Long id, BigDecimal amount,
                                       TransactionType type, Category category,
                                       String description) {

        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setCategory(category);
        transaction.setDescription(description);

        return transaction;
    }

    private TransactionListResponse createTestTransactionListResponse(List<Transaction> transactions) {
        TransactionListResponse transactionListResponse = new TransactionListResponse();
        transactionListResponse.setTransactions(transactions.stream()
                .map(this::createTestTransactionResponse)
                .toList());

        return transactionListResponse;
    }

    private TransactionResponse createTestTransactionResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setType(transaction.getType());
        response.setAmount(transaction.getAmount());
        response.setCategory(transaction.getCategory());
        response.setDescription(transaction.getDescription());
        response.setDate(transaction.getDate());

        return response;
    }
}
