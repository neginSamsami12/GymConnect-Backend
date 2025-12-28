package com.gymconnect.user.controller;

import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.user.dto.UserCreateRequest;
import com.gymconnect.user.dto.UserResponse;
import com.gymconnect.user.dto.UserUpdateRequest;
import com.gymconnect.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> create(
            @Valid @RequestBody UserCreateRequest request
    ) {
        UserResponse result = userService.create(request);
        return ApiResponse.success(result, "User created successfully");
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        UserResponse result = userService.update(id, request);
        return ApiResponse.success(result, "User updated successfully");
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> findAll() {
        return ApiResponse.success(userService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findById(@PathVariable UUID id) {
        return ApiResponse.success(userService.findById(id));
    }
}
