package com.gymconnect.user.dto;

import com.gymconnect.user.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserUpdateRequest(

        @Size(max = 100)
        String firstName,

        @Size(max = 100)
        String lastName,

        @Size(max = 120)
        String email,

        @Size(max = 20)
        String phone,

        LocalDate birthDate,

        Gender gender,

        String address,

        @Size(max = 15)
        String nationalId
) {}
