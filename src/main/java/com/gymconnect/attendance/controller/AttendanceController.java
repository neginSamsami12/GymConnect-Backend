package com.gymconnect.attendance.controller;

import com.gymconnect.attendance.dto.AttendanceCreateRequest;
import com.gymconnect.attendance.service.AttendanceService;
import com.gymconnect.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid AttendanceCreateRequest req) {
        return ResponseEntity.ok(attendanceService.create(req));
    }

    @GetMapping("/today")
    public ResponseEntity<ApiResponse> getTodayAttendance() {

        ApiResponse result = attendanceService.getTodayAttendances();

        return ResponseEntity.ok(result);
    }
}
