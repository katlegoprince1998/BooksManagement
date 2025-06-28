package com.qda.user_service.mapper;

import com.qda.user_service.dto.request.CreateUserRequest;
import com.qda.user_service.dto.response.UserResponse;
import com.qda.user_service.entitty.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUserEntity(CreateUserRequest request){
        return User
                .builder()
                .fullName(request.fullName())
                .email(request.email())
                .phoneNumber(request.phone())
                .role(request.role())
                .gender(request.gender())
                .build();
    }

    public UserResponse toUserDto(User user){
        return new UserResponse(
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getGender(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.isUserProfileIsActive()
        );
    }
}
