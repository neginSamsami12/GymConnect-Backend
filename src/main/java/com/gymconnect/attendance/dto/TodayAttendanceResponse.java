package com.gymconnect.attendance.dto;
import java.util.UUID;

public record TodayAttendanceResponse(
        UUID id,
        String firstName,
        String lastName,
        String className,
        String checkInTime,
        String checkOuTime
) {}
