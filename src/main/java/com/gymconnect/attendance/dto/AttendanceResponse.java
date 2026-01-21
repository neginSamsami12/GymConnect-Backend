package com.gymconnect.attendance.dto;

import java.time.Instant;
import java.util.UUID;

public record AttendanceResponse(
        UUID id,
        String name,
        String lastName,
        String className,
        Instant entranceTime,
        Instant exitTime
) {}
