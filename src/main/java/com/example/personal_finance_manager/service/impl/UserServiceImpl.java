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

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final UserEntityHelper entityHelper;

    @Override
    public UserListResponse findAll() {
        return userMapper.userListToUserListResponse(repository.findAll());
    }

    @Override
    public UserResponse findById(Long id) {
        return userMapper.userToUserResponse(getUserById(id, false));
    }

    @Override
    public UserDetailedResponse findByIdWithTransactions(Long id) {
        return userMapper.userToUserDetailedResponse(getUserById(id, true));
    }

    public User getUserById(Long id, boolean withTransactions) {
        return entityHelper.getUserById(id, withTransactions);
    }

    @Override
    public UserResponse save(UpsertUserRequest request) {
        User newUser = repository.save(userMapper.userRequestToUser(request));
        return userMapper.userToUserResponse(newUser);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UpsertUserRequest request) {
        User user = userMapper.userRequestToUser(request);
        User existedUser = getUserById(id, false);
        BeanUtils.copyNonNullProperties(user, existedUser);
        return userMapper.userToUserResponse(repository.save(existedUser));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
