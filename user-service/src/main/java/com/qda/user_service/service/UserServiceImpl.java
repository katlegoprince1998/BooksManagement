package com.qda.user_service.service;

import com.qda.user_service.dto.request.CreateUserRequest;
import com.qda.user_service.dto.request.UpdateUserRequest;
import com.qda.user_service.dto.response.UserResponse;
import com.qda.user_service.entitty.User;
import com.qda.user_service.exception.FailedToCreatUserException;
import com.qda.user_service.exception.FailedToUpdateUserException;
import com.qda.user_service.exception.UserNotFoundException;
import com.qda.user_service.mapper.UserMapper;
import com.qda.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public UserResponse createUser(CreateUserRequest request) throws FailedToCreatUserException {
        if (request == null) {
            log.warn("Create user request is null");
            throw new FailedToCreatUserException("User creation failed: request cannot be null");
        }

        if (emailExists(request.email())) {
            log.warn("Email already exists: {}", request.email());
            throw new FailedToCreatUserException("Email already exists. Please use a different email.");
        }

        try {
            User user = UserMapper.toUserEntity(request);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setUserProfileIsActive(true);
            User savedUser = repository.save(user);
            log.info("User created successfully: {}", savedUser.getUserId());
            return UserMapper.toUserDto(savedUser);
        } catch (Exception e) {
            log.error("Error occurred while creating user: {}", e.getMessage(), e);
            throw new FailedToCreatUserException("Error creating user: " + e.getMessage());
        }
    }

    @Override
    public UserResponse findUserById(Long id) throws UserNotFoundException {
        if (id == null) {
            log.warn("Attempted to find user with null ID");
            throw new UserNotFoundException("User ID cannot be null");
        }

        return repository.findById(id)
                .map(UserMapper::toUserDto)
                .orElseThrow(() -> {
                    log.warn("User not found with ID: {}", id);
                    return new UserNotFoundException("User not found with ID: " + id);
                });
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest request, Long userId) throws FailedToUpdateUserException {
        if (request == null) {
            log.warn("Update user request is null");
            throw new FailedToUpdateUserException("Update request cannot be null");
        }

        try {
            User user = repository.findById(userId)
                    .orElseThrow(() -> {
                        log.warn("User not found with ID: {}", userId);
                        return new UserNotFoundException("User not found with ID: " + userId);
                    });

            updateUserFields(user, request);
            User updatedUser = repository.save(user);

            log.info("User updated successfully: {}", updatedUser.getUserId());
            return UserMapper.toUserDto(updatedUser);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error occurred while updating user: {}", e.getMessage(), e);
            throw new FailedToUpdateUserException("Failed to update user: " + e.getMessage());
        }
    }

    // Private helper methods

    private boolean emailExists(String email) {
        return repository.findUserByEmail(email) != null;
    }

    private void updateUserFields(User user, UpdateUserRequest request) {
        if (request.email() != null) {
            user.setEmail(request.email());
        }
        if (request.phone() != null) {
            user.setPhoneNumber(request.phone());
        }
        if (request.fullName() != null) {
            user.setFullName(request.fullName());
        }
    }
}
