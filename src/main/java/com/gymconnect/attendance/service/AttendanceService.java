package com.gymconnect.attendance.service;

import com.gymconnect.attendance.dto.AttendanceCreateRequest;
import com.gymconnect.common.response.ApiResponse;



public interface AttendanceService {
    ApiResponse getTodayAttendances();
    ApiResponse create(AttendanceCreateRequest req);
}
