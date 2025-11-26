package com.example.personal_finance_manager.service;

import com.example.personal_finance_manager.dto.request.UpsertUserRequest;
import com.example.personal_finance_manager.dto.response.UserDetailedResponse;
import com.example.personal_finance_manager.dto.response.UserListResponse;
import com.example.personal_finance_manager.dto.response.UserResponse;

/**
 * Service interface for user management operations.
 * Defines business logic methods for user-related operations.
 *
 * @author Evgeny Zhulpa
 * @version 1.0
 * @since 1.0
 */
public interface UserService {
    
    /**
     * Retrieves all users from the system.
     *
     * @return UserListResponse containing list of all users
     */
    UserListResponse findAll();
    
    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user
     * @return UserResponse containing user details
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if user with the specified id is not found
     */
    UserResponse findById(Long id);
    
    /**
     * Retrieves a user by their unique identifier including all associated transactions.
     *
     * @param id the unique identifier of the user
     * @return UserDetailedResponse containing user details with transactions
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if user with the specified id is not found
     */
    UserDetailedResponse findByIdWithTransactions(Long id);
    
    /**
     * Creates a new user in the system.
     *
     * @param request the request DTO containing user data to create
     * @return UserResponse containing created user details
     */
    UserResponse save(UpsertUserRequest request);
    
    /**
     * Updates an existing user by their unique identifier.
     *
     * @param id the unique identifier of the user to update
     * @param request the request DTO containing updated user data
     * @return UserResponse containing updated user details
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if user with the specified id is not found
     */
    UserResponse update(Long id, UpsertUserRequest request);
    
    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the unique identifier of the user to delete
     */
    void deleteById(Long id);
}
