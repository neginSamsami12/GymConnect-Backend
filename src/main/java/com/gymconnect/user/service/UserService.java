package com.gymconnect.user.service;

import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.user.dto.UserCreateRequest;
import com.gymconnect.user.dto.UserResponse;
import com.gymconnect.user.dto.UserUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UserService {
    ApiResponse create(UserCreateRequest request, MultipartFile image);

    ApiResponse update(UUID id, UserUpdateRequest request, MultipartFile image);

    ApiResponse findById(UUID id);

    ApiResponse findAll();

    UserResponse deleteUser(UUID uuid);
}
