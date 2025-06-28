package com.qda.user_service.dto.response;

import com.qda.user_service.entitty.Gender;
import com.qda.user_service.entitty.Role;

import java.time.LocalDateTime;

public record UserResponse(
        Long userId,
        String fullName,
        String email,
        String phone,
        Gender gender,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean userProfileIsActive

) {
}
