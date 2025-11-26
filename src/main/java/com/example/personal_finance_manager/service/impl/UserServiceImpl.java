package com.example.personal_finance_manager.service.impl;

import com.example.personal_finance_manager.dto.request.UpsertUserRequest;
import com.example.personal_finance_manager.dto.response.UserDetailedResponse;
import com.example.personal_finance_manager.dto.response.UserListResponse;
import com.example.personal_finance_manager.dto.response.UserResponse;
import com.example.personal_finance_manager.mapper.UserMapper;
import com.example.personal_finance_manager.model.User;
import com.example.personal_finance_manager.repository.UserRepository;
import com.example.personal_finance_manager.service.UserService;
import com.example.personal_finance_manager.utils.BeanUtils;
import com.example.personal_finance_manager.utils.UserEntityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of UserService interface.
 * Provides business logic for user management operations including CRUD operations.
 *
 * <p>This service handles user-related operations such as creating, reading, updating,
 * and deleting users, as well as retrieving users with their associated transactions.
 *
 * @author Evgeny Zhulpa
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * Repository for user data access operations.
     */
    private final UserRepository repository;
    
    /**
     * Mapper for converting between User entity and DTOs.
     */
    private final UserMapper userMapper;
    
    /**
     * Helper utility for user entity operations.
     */
    private final UserEntityHelper entityHelper;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserListResponse findAll() {
        return userMapper.userListToUserListResponse(repository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse findById(Long id) {
        return userMapper.userToUserResponse(getUserById(id, false));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetailedResponse findByIdWithTransactions(Long id) {
        return userMapper.userToUserDetailedResponse(getUserById(id, true));
    }

    /**
     * Retrieves a user entity by ID, optionally with transactions.
     *
     * @param id the unique identifier of the user
     * @param withTransactions if true, loads user with transactions; otherwise loads without
     * @return User entity
     * @throws com.example.personal_finance_manager.exception.EntityNotFoundException
     *         if user with the specified id is not found
     */
    public User getUserById(Long id, boolean withTransactions) {
        return entityHelper.getUserById(id, withTransactions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserResponse save(UpsertUserRequest request) {
        User newUser = repository.save(userMapper.userRequestToUser(request));
        return userMapper.userToUserResponse(newUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UserResponse update(Long id, UpsertUserRequest request) {
        User user = userMapper.userRequestToUser(request);
        User existedUser = getUserById(id, false);
        BeanUtils.copyNonNullProperties(user, existedUser);
        return userMapper.userToUserResponse(repository.save(existedUser));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
