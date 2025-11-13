package com.example.personal_finance_manager.utils;

import com.example.personal_finance_manager.exception.EntityNotFoundException;
import com.example.personal_finance_manager.model.User;
import com.example.personal_finance_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserEntityHelper {

    private final UserRepository repository;

    public final User getUserById(Long id, boolean withTransactions) {
        Optional<User> userOptional = withTransactions ?
                repository.findWithTransactionsById(id) : repository.findById(id);
        return userOptional
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Пользователь с id {0} не найден!", id)));
    }
}
