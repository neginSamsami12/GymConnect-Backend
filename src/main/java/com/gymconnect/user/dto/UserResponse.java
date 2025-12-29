package com.gymconnect.user.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        LocalDate birthDate,
        String address,
        String nationalId,
        Instant createdAt
) {}
