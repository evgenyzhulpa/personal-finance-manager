package com.example.personal_finance_manager.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TransactionListResponse {
    private List<TransactionResponse> transactions = new ArrayList<>();
}
