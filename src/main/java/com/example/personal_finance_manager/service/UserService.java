package com.example.personal_finance_manager.service;

import com.example.personal_finance_manager.dto.request.UpsertUserRequest;
import com.example.personal_finance_manager.dto.response.UserDetailedResponse;
import com.example.personal_finance_manager.dto.response.UserListResponse;
import com.example.personal_finance_manager.dto.response.UserResponse;

public interface UserService {
    UserListResponse findAll();
    UserResponse findById(Long id);
    UserDetailedResponse findByIdWithTransactions(Long id);
    UserResponse save(UpsertUserRequest request);
    UserResponse update(Long id, UpsertUserRequest request);
    void deleteById(Long id);
}
