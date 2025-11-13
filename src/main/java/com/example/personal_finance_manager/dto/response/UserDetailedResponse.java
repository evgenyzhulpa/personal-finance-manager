package com.example.personal_finance_manager.dto.response;

import com.example.personal_finance_manager.model.Transaction;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDetailedResponse {
    private Long id;
    private String username;
    private String email;
    List<TransactionResponse> transactions = new ArrayList<>();
}
