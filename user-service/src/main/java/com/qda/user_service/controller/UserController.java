package com.qda.user_service.controller;

import com.qda.user_service.dto.request.CreateUserRequest;
import com.qda.user_service.dto.request.UpdateUserRequest;
import com.qda.user_service.dto.response.UserResponse;
import com.qda.user_service.exception.FailedToCreatUserException;
import com.qda.user_service.exception.FailedToUpdateUserException;
import com.qda.user_service.exception.UserNotFoundException;
import com.qda.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request)
            throws FailedToCreatUserException {
        UserResponse createdUser = userService.createUser(request);
        return ResponseEntity.status(201).body(createdUser); // 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id)
            throws UserNotFoundException {
        UserResponse user = userService.findUserById(id);
        return ResponseEntity.ok(user); // 200 OK
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request
    ) throws FailedToUpdateUserException {
        UserResponse updatedUser = userService.updateUser(request, id);
        return ResponseEntity.ok(updatedUser); // 200 OK
    }
}