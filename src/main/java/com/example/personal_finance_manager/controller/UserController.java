package com.example.personal_finance_manager.controller;

import com.example.personal_finance_manager.dto.request.UpsertUserRequest;
import com.example.personal_finance_manager.dto.response.UserDetailedResponse;
import com.example.personal_finance_manager.dto.response.UserListResponse;
import com.example.personal_finance_manager.dto.response.UserResponse;
import com.example.personal_finance_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing users in the system.
 * Provides CRUD operations for user management through REST API endpoints.
 *
 * <p>All endpoints are available under the base path {@code /api/users}.
 *
 * @author Evgeny Zhulpa
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    /**
     * Service for user-related business logic operations.
     */
    private final UserService userService;

    /**
     * Retrieves all users from the system.
     *
     * @return ResponseEntity containing UserListResponse with list of all users
     *         and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<UserListResponse> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user to retrieve
     * @return ResponseEntity containing UserResponse with user details
     *         and HTTP status 200 (OK)
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if user with the specified id is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    /**
     * Retrieves a user by their unique identifier including all associated transactions.
     *
     * @param id the unique identifier of the user to retrieve
     * @return ResponseEntity containing UserDetailedResponse with user details and transactions
     *         and HTTP status 200 (OK)
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if user with the specified id is not found
     */
    @GetMapping("/{id}/with-transactions")
    public ResponseEntity<UserDetailedResponse> findByIdWithTransactions(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findByIdWithTransactions(id));
    }

    /**
     * Creates a new user in the system.
     *
     * @param request the request DTO containing user data to create
     * @return ResponseEntity containing UserResponse with created user details
     *         and HTTP status 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UpsertUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(request));
    }

    /**
     * Updates an existing user by their unique identifier.
     *
     * @param id the unique identifier of the user to update
     * @param request the request DTO containing updated user data
     * @return ResponseEntity containing UserResponse with updated user details
     *         and HTTP status 200 (OK)
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if user with the specified id is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                               @RequestBody UpsertUserRequest request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the unique identifier of the user to delete
     * @return ResponseEntity with no content and HTTP status 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
