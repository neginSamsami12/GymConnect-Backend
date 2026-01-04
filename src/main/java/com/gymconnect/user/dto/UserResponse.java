package com.gymconnect.user.dto;

import com.gymconnect.user.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        LocalDate birthDate,
        Gender gender,
        String address,
        String nationalId,
        String imageUrl
) {}
