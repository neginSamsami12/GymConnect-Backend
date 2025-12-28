package com.gymconnect.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserCreateRequest(

        @NotBlank
        @Size(max = 100)
        String firstName,

        @NotBlank
        @Size(max = 100)
        String lastName,

        @Size(max = 120)
        String email,

        @Size(max = 20)
        String phone,

        LocalDate birthDate,

        String address,

        @NotBlank
        @Size(max = 15)
        String nationalId
) {}
