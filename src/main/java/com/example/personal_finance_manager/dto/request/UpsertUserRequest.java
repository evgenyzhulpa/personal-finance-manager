package com.example.personal_finance_manager.dto.request;

import lombok.Data;

@Data
public class UpsertUserRequest {
    private String username;
    private String password;
    private String email;
}
