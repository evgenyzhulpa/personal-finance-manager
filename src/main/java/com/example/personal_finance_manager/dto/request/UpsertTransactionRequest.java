package com.example.personal_finance_manager.dto.request;

import com.example.personal_finance_manager.model.Category;
import com.example.personal_finance_manager.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UpsertTransactionRequest {
    private BigDecimal amount;
    private TransactionType type;
    private Category category;
    private String description;
    private LocalDateTime date;
    private Long userId;
}
