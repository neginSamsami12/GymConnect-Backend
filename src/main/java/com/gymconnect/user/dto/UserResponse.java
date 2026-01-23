package com.gymconnect.user.dto;

import com.gymconnect.user.enums.Gender;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String nationalId;
    private Instant registrationDate;
    private Boolean isActive;
    private String imageUrl;
}
