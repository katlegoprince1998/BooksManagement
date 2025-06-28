package com.qda.user_service.service;

import com.qda.user_service.dto.request.CreateUserRequest;
import com.qda.user_service.dto.request.UpdateUserRequest;
import com.qda.user_service.dto.response.UserResponse;
import com.qda.user_service.exception.FailedToCreatUserException;
import com.qda.user_service.exception.FailedToUpdateUserException;
import com.qda.user_service.exception.UserNotFoundException;

public interface UserService {
    UserResponse createUser(CreateUserRequest request) throws FailedToCreatUserException;
    UserResponse findUserById(Long id) throws UserNotFoundException;
    UserResponse updateUser(UpdateUserRequest request, Long userId) throws FailedToUpdateUserException;

}
