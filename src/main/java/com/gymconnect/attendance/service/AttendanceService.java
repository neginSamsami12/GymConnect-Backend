package com.gymconnect.attendance.service;

import com.gymconnect.attendance.dto.AttendanceCreateRequest;
import com.gymconnect.common.response.ApiResponse;

import java.util.UUID;


public interface AttendanceService {
    ApiResponse getTodayAttendances();
    ApiResponse create(AttendanceCreateRequest req);
    ApiResponse checkOut(UUID id);
    ApiResponse getWeeklyAttendance();
}
