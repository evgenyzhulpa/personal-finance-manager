package com.example.personal_finance_manager.mapper;

import com.example.personal_finance_manager.dto.request.UpsertUserRequest;
import com.example.personal_finance_manager.dto.response.UserDetailedResponse;
import com.example.personal_finance_manager.dto.response.UserListResponse;
import com.example.personal_finance_manager.dto.response.UserResponse;
import com.example.personal_finance_manager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TransactionMapper.class})
public interface UserMapper {
    User userRequestToUser(UpsertUserRequest request);
    @Mapping(source = "userId", target = "id")
    User userRequestToUser(Long userId, UpsertUserRequest request);
    UserResponse userToUserResponse(User user);
    UserDetailedResponse userToUserDetailedResponse(User user);
    List<UserResponse> userListToUserResponseList(List<User> users);
    default UserListResponse userListToUserListResponse(List<User> users) {
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUsers(userListToUserResponseList(users));
        return userListResponse;
    }
}
