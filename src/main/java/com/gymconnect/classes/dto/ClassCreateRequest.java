package com.gymconnect.classes.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ClassCreateRequest(

        @NotNull
        UUID trainerId,

        @NotNull
        @Size(max = 150)
        String title,

        String description,

        @NotNull
        Integer capacity,

        String days,

        @NotNull
        LocalTime scheduleTime,

        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate endDate,

        @NotNull
        BigDecimal price
) {}
