package com.example.personal_finance_manager.service;

import com.example.personal_finance_manager.dto.response.UserResponse;
import com.example.personal_finance_manager.mapper.UserMapper;
import com.example.personal_finance_manager.model.User;
import com.example.personal_finance_manager.repository.UserRepository;
import com.example.personal_finance_manager.service.impl.UserServiceImpl;
import com.example.personal_finance_manager.utils.UserEntityHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserEntityHelper entityHelper;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Test findById method")
    public void testFindById() {
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setUsername("User1");
        user.setEmail("user1@gmail.com");
        user.setPassword("1111");
        user.setTransactions(List.of());

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());

        when(entityHelper.getUserById(id, false)).thenReturn(user);
        when(userMapper.userToUserResponse(user)).thenReturn(userResponse);
        assertEquals(user.getId(), userService.findById(id).getId());
        verify(entityHelper, times(1)).getUserById(id, false);

    }
}
