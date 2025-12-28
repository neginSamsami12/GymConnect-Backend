package com.gymconnect.user.service;

import com.gymconnect.user.dto.UserCreateRequest;
import com.gymconnect.user.dto.UserResponse;
import com.gymconnect.user.dto.UserUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse create(UserCreateRequest request);

    UserResponse update(UUID id, UserUpdateRequest request);

    UserResponse findById(UUID id);

    List<UserResponse> findAll();
}
