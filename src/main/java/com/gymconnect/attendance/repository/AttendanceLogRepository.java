package com.gymconnect.attendance.repository;

import com.gymconnect.attendance.dto.TodayAttendanceResponse;
import com.gymconnect.attendance.entity.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, UUID> {

    @Query("""
    SELECT a
    FROM AttendanceLog a
    JOIN FETCH a.user
    JOIN FETCH a.classField
    WHERE a.checkIn >= :startOfDay
      AND a.checkIn < :endOfDay
""")
    List<AttendanceLog> findAttendanceLogBetweenDays(
            @Param("startOfDay") Instant startOfDay,
            @Param("endOfDay") Instant endOfDay
    );

}

