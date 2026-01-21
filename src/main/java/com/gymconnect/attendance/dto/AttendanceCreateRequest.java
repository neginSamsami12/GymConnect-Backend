package com.gymconnect.attendance.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AttendanceCreateRequest(
        @NotNull UUID userId,
        @NotNull UUID classId
) {}
