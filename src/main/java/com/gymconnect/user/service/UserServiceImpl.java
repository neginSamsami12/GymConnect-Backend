package com.gymconnect.user.service;

import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.common.storage.FileStorageService;
import com.gymconnect.user.dto.*;
import com.gymconnect.user.entity.User;
import com.gymconnect.user.mapper.UserMapper;
import com.gymconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final FileStorageService fileStorageService;

    @Override
    @Transactional
    public ApiResponse create(UserCreateRequest request, MultipartFile image) {

        if (userRepository.existsByNationalId(request.nationalId())) {
            throw new IllegalArgumentException("User with nationalId already exists");
        }

        User user = userMapper.toEntity(request);

        // todo: replace with real password flow
        user.setPasswordHash(passwordEncoder.encode("123456"));

        userRepository.save(user);

        if (image != null && !image.isEmpty()) {
            String imageUrl = fileStorageService.storeProfileImage(user.getId(), image);
            user.setImageUrl(imageUrl);
        }

        UserResponse response = userMapper.toResponse(user);
        return ApiResponse.success(response);
    }

    @Override
    @Transactional
    public ApiResponse update(UUID id,
                              UserUpdateRequest request,
                              MultipartFile image) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // update fields
        userMapper.updateEntity(request, user);

        // update image if provided
        if (image != null && !image.isEmpty()) {
            String imageUrl = fileStorageService.storeProfileImage(user.getId(), image);
            user.setImageUrl(imageUrl);
        }

        UserResponse response = userMapper.toResponse(user);
        return ApiResponse.success(response);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse findById(UUID id) {

        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserResponse user = userMapper.toResponse(userEntity);
        return ApiResponse.success(user);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse findAll() {

        List<User> userEntities = userRepository.findAll();
        List<UserResponse> users = userMapper.toResponseList(userEntities);

        return ApiResponse.success(users);
    }
}
