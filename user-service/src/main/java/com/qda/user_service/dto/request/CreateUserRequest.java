package com.qda.user_service.dto.request;

import com.qda.user_service.entitty.Gender;
import com.qda.user_service.entitty.Role;

public record CreateUserRequest(
        String fullName,
        String email,
        String phone,
        Gender gender,
        Role role
) {
}
