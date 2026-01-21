package com.gymconnect.classes.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
public record ClassResponse(
        UUID id,
        String trainerName,
        String title,
        String description,
        Integer capacity,
        String days,
        LocalTime scheduleTime,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal price,
        String imageUrl
) {}
