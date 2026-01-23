package com.gymconnect.attendance.service;

import com.gymconnect.attendance.dto.AttendanceCreateRequest;
import com.gymconnect.attendance.dto.TodayAttendanceResponse;
import com.gymconnect.attendance.dto.WeeklyAttendanceResponse;
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

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceLogRepository attendanceLogRepository;
    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    private final AttendanceMapper attendanceMapper;

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm");

    @Transactional
    public ApiResponse create(AttendanceCreateRequest req) {

        User user = userRepository.findById(req.userId())
                .orElseThrow(() -> new IllegalArgumentException("USER_NOT_FOUND"));

        Class clazz = classRepository.findById(req.classId())
                .orElseThrow(() -> new IllegalArgumentException("CLASS_NOT_FOUND"));

        AttendanceLog log = AttendanceLog.builder()
                .classField(clazz)
                .user(user)
                .checkIn(Instant.now())
                .build();


        attendanceLogRepository.save(log);

        return ApiResponse.success(null);
    }

    @Override
    public ApiResponse checkOut(UUID id) {
        AttendanceLog checkOut = attendanceLogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ATTENDANCE_LOG_NOT_FIND"));
        checkOut.setCheckOut(Instant.now());
        attendanceLogRepository.save(checkOut);

        return ApiResponse.success(null);
    }

    @Override
    public ApiResponse getTodayAttendances() {

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate today = LocalDate.now(zoneId);

        Instant startOfDay = today.atStartOfDay(zoneId).toInstant();
        Instant endOfDay = today.plusDays(1).atStartOfDay(zoneId).toInstant();

        List<TodayAttendanceResponse> response =
                attendanceLogRepository
                        .findAttendanceLogBetweenDays(startOfDay, endOfDay)
                        .stream()
                        .map(a -> new TodayAttendanceResponse(
                                a.getId(),
                                a.getUser().getFirstName(),
                                a.getUser().getLastName(),
                                a.getClassField().getTitle(),
                                formatTime(a.getCheckIn(), zoneId),
                                formatTime(a.getCheckOut(), zoneId)
                        ))
                        .toList();

        return ApiResponse.success(response);
    }

    private String formatTime(Instant instant, ZoneId zoneId) {
        if (instant == null) {
            return null;
        }
        return instant
                .atZone(zoneId)
                .toLocalTime()
                .format(TIME_FORMATTER);
    }

    @Override
    public ApiResponse getWeeklyAttendance() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate today = LocalDate.now(zoneId);
        LocalDate startOfWeek = today.minusDays(6);
        Instant startOfWeekInstant = startOfWeek.atStartOfDay(zoneId).toInstant();
        Instant endOfWeekInstant = today.plusDays(1).atStartOfDay(zoneId).toInstant();

        List<WeeklyAttendanceResponse> response = attendanceLogRepository
                .findAttendanceLogBetweenDays(startOfWeekInstant, endOfWeekInstant)
                .stream()
                .map(a -> new WeeklyAttendanceResponse(
                        a.getId(),
                        a.getUser().getFirstName(),
                        a.getUser().getLastName(),
                        a.getClassField().getTitle(),
                        a.getCheckIn().atZone(zoneId).toLocalDate()
                ))
                .toList();

        return ApiResponse.success(response);
    }

}
