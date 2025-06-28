package com.qda.user_service.dto.request;

import com.qda.user_service.entitty.Gender;

public record UpdateUserRequest(
        String fullName,
        String email,
        String phone
) {
}
