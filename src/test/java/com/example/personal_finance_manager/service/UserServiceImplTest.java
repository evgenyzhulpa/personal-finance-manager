package com.example.personal_finance_manager.service;

import com.example.personal_finance_manager.dto.response.TransactionResponse;
import com.example.personal_finance_manager.dto.response.UserDetailedResponse;
import com.example.personal_finance_manager.dto.response.UserListResponse;
import com.example.personal_finance_manager.dto.response.UserResponse;
import com.example.personal_finance_manager.exception.EntityNotFoundException;
import com.example.personal_finance_manager.mapper.UserMapper;
import com.example.personal_finance_manager.model.Category;
import com.example.personal_finance_manager.model.Transaction;
import com.example.personal_finance_manager.model.TransactionType;
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

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    @DisplayName("Should return all users")
    public void givenExistingUsers_whenFindAll_thenReturnUserListResponse() {
        Long id = 1L;
        List<User> users = List.of(createTestUser(id));
        UserListResponse userListResponse = createTestUserListResponse(users);

        when(repository.findAll()).thenReturn(users);
        when(userMapper.userListToUserListResponse(users)).thenReturn(userListResponse);

        assertThat(userListResponse.getUsers()).isEqualTo(userService.findAll().getUsers());
        verify(repository, times(1)).findAll();
        verify(userMapper, times(1)).userListToUserListResponse(users);
    }

    @Test
    @DisplayName("Should return user response when user exists")
    public void givenExistingUser_whenFindById_thenReturnUserResponse() {
        Long id = 1L;
        User user = createTestUser(id);
        UserResponse userResponse = createTestUserResponse(user);

        when(entityHelper.getUserById(id, false)).thenReturn(user);
        when(userMapper.userToUserResponse(user)).thenReturn(userResponse);
        
        assertThat(userResponse).isEqualTo(userService.findById(id));
        verify(entityHelper, times(1)).getUserById(id, false);
        verify(userMapper, times(1)).userToUserResponse(user);
    }

    @Test
    @DisplayName("Should throw exception when user is not found")
    public void givenNonExistingUserId_whenFindById_thenThrowException() {
        Long nonExistingUserId = 999L;
        String errorMessage = MessageFormat.format("User with id {0} is not found!",
                nonExistingUserId);

        when(entityHelper.getUserById(nonExistingUserId, false)).thenThrow(
                new EntityNotFoundException(errorMessage));

        assertThatThrownBy(() -> userService.findById(nonExistingUserId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage(errorMessage);
        verify(entityHelper, times(1)).getUserById(nonExistingUserId, false);
    }

    @Test
    @DisplayName("Should return detailed user response when user exists")
    public void givenExistingUser_whenFindByIdWithTransactions_ThenReturnUserDetailedResponse() {
        Long id = 1L;
        User user = createTestUserWithTransactions(id);
        UserDetailedResponse userDetailedResponse = createTestUserDetailedResponse(user);

        when(entityHelper.getUserById(id, true)).thenReturn(user);
        when(userMapper.userToUserDetailedResponse(user)).thenReturn(userDetailedResponse);

        assertThat(userService.findByIdWithTransactions(id)).isEqualTo(userDetailedResponse);
        verify(entityHelper, times(1)).getUserById(id, true);
        verify(userMapper, times(1)).userToUserDetailedResponse(user);
    }

    private User createTestUser(Long id) {
        return User.builder()
                .id(id)
                .username("testuser")
                .email("testuser@mail.com")
                .password("encodedPass123")
                .build();
    }

    private UserListResponse createTestUserListResponse(List<User> users) {
        UserListResponse userListResponse = new UserListResponse();
        List<UserResponse> userResponses = users.stream()
                .map(this::createTestUserResponse)
                .collect(Collectors.toList());
        userListResponse.setUsers(userResponses);
        return userListResponse;
    }

    private UserResponse createTestUserResponse (User testUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(testUser.getId());
        userResponse.setUsername(testUser.getUsername());
        userResponse.setEmail(testUser.getEmail());
        return userResponse;
    }

    private User createTestUserWithTransactions(Long id) {
        User user = createTestUser(id);
        Transaction transaction = createTestTransaction(user);
        user.setTransactions(List.of(transaction));
        return user;
    }

    private Transaction createTestTransaction(User user) {
        Long id = 1L;
        return Transaction.builder()
                .id(id)
                .user(user)
                .amount(new BigDecimal(100000))
                .category(Category.OTHER)
                .type(TransactionType.INCOME)
                .date(LocalDateTime.now())
                .description("This is the test transaction")
                .build();
    }

    private UserDetailedResponse createTestUserDetailedResponse(User user) {
        UserDetailedResponse userDetailedResponse = new UserDetailedResponse();
        userDetailedResponse.setId(user.getId());
        userDetailedResponse.setUsername(user.getUsername());
        userDetailedResponse.setEmail(user.getEmail());

        List<TransactionResponse> transactionResponses = user.getTransactions()
                .stream()
                .map(this::createTestTransactionResponse)
                .toList();
        userDetailedResponse.setTransactions(transactionResponses);

        return userDetailedResponse;
    }

    private TransactionResponse createTestTransactionResponse(Transaction transaction) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(transaction.getId());
        transactionResponse.setType(transaction.getType());
        transactionResponse.setDate(transaction.getDate());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setCategory(transaction.getCategory());
        transactionResponse.setDescription(transaction.getDescription());
        return transactionResponse;
    }
}
