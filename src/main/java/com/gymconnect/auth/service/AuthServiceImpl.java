package com.gymconnect.auth.service;

import com.gymconnect.auth.dto.LoginRequest;
import com.gymconnect.auth.dto.LoginResponse;
import com.gymconnect.auth.security.UserPrincipal;
import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.config.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public ApiResponse login(LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.nationalId(),
                        request.password()
                )
        );

        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        String token = jwtProvider.generateToken(principal);

        return ApiResponse.success(
                new LoginResponse(token)
        );
    }
}
