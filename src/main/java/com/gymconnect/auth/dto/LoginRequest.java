package com.gymconnect.auth.dto;

public record LoginRequest (
        String nationalId,
        String password
) {}
