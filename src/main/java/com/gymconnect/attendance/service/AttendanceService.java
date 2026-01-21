package com.gymconnect.attendance.service;

import com.gymconnect.attendance.dto.AttendanceCreateRequest;
import com.gymconnect.attendance.dto.AttendanceResponse;
import com.gymconnect.attendance.mapper.AttendanceMapper;
import com.gymconnect.attendance.repository.AttendanceLogRepository;
import com.gymconnect.classes.repository.ClassRepository;
import com.gymconnect.attendance.entity.AttendanceLog;
import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.user.entity.User;
import com.gymconnect.classes.entity.Class;
import com.gymconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceLogRepository attendanceLogRepository;
    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    private final AttendanceMapper attendanceMapper;

    @Transactional
    public ApiResponse create(AttendanceCreateRequest req) {

        User user = userRepository.findById(req.userId())
                .orElseThrow(() -> new IllegalArgumentException("USER_NOT_FOUND"));

        Class clazz = classRepository.findById(req.classId())
                .orElseThrow(() -> new IllegalArgumentException("CLASS_NOT_FOUND"));

        AttendanceLog log = AttendanceLog.builder()
                .classField(clazz)
                .user(user)
                .build();

        attendanceLogRepository.save(log);

        return ApiResponse.success(null);
    }
}
