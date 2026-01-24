package com.gymconnect.attendance.dto;

import java.time.LocalDate;
import java.util.UUID;

public record WeeklyAttendanceResponse(
        UUID id,
        String firstName,
        String lastName,
        String className,
        LocalDate date
) {
}
