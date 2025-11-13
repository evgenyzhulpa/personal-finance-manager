package com.example.personal_finance_manager.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
}
