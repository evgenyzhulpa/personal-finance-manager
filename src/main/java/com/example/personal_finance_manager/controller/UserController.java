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

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/{id}/with-transactions")
    public ResponseEntity<UserDetailedResponse> findByIdWithTransactions(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findByIdWithTransactions(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UpsertUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                               @RequestBody UpsertUserRequest request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
