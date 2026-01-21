package com.gymconnect.attendance.mapper;

import com.gymconnect.attendance.dto.AttendanceResponse;
import com.gymconnect.attendance.entity.AttendanceLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

//    @Mapping(target = "userId", source = "user.id")
//    @Mapping(target = "classId", source = "classField")
//    AttendanceResponse toResponse(AttendanceLog entity);
}
