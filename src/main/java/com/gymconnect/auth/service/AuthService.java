package com.gymconnect.auth.service;

import com.gymconnect.auth.dto.LoginRequest;
import com.gymconnect.common.response.ApiResponse;

public interface AuthService {
    ApiResponse login(LoginRequest request);
}
