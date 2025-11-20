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

/**
 * MapStruct mapper interface for converting between User entity and DTOs.
 * Provides mapping methods for user-related data transformations.
 *
 * <p>This mapper handles conversions between:
 * <ul>
 *   <li>User entity and UserResponse DTO</li>
 *   <li>User entity and UserDetailedResponse DTO</li>
 *   <li>UpsertUserRequest DTO and User entity</li>
 *   <li>List of User entities and UserListResponse DTO</li>
 * </ul>
 *
 * @author Personal Finance Manager Team
 * @version 1.0
 * @since 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TransactionMapper.class})
public interface UserMapper {
    
    /**
     * Maps UpsertUserRequest DTO to User entity.
     *
     * @param request the request DTO containing user data
     * @return User entity
     */
    User userRequestToUser(UpsertUserRequest request);
    
    /**
     * Maps UpsertUserRequest DTO to User entity with specified ID.
     *
     * @param userId the user ID to set
     * @param request the request DTO containing user data
     * @return User entity with specified ID
     */
    @Mapping(source = "userId", target = "id")
    User userRequestToUser(Long userId, UpsertUserRequest request);
    
    /**
     * Maps User entity to UserResponse DTO.
     *
     * @param user the User entity to map
     * @return UserResponse DTO
     */
    UserResponse userToUserResponse(User user);
    
    /**
     * Maps User entity to UserDetailedResponse DTO including transactions.
     *
     * @param user the User entity to map
     * @return UserDetailedResponse DTO with transactions
     */
    UserDetailedResponse userToUserDetailedResponse(User user);
    
    /**
     * Maps list of User entities to list of UserResponse DTOs.
     *
     * @param users the list of User entities to map
     * @return list of UserResponse DTOs
     */
    List<UserResponse> userListToUserResponseList(List<User> users);
    
    /**
     * Maps list of User entities to UserListResponse DTO.
     *
     * @param users the list of User entities to map
     * @return UserListResponse DTO containing list of UserResponse DTOs
     */
    default UserListResponse userListToUserListResponse(List<User> users) {
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUsers(userListToUserResponseList(users));
        return userListResponse;
    }
}
