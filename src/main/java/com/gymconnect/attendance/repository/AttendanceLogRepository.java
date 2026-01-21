package com.gymconnect.attendance.repository;

import com.gymconnect.attendance.entity.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, UUID> {
}