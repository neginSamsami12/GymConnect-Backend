package com.gymconnect.classes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ClassResponse(
        UUID id,
        UUID trainerId,
        String title,
        String description,
        Integer capacity,
        LocalTime scheduleTime,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal price,
        String imageUrl
) {}
