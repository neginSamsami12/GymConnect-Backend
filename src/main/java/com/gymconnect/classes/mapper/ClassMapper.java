package com.gymconnect.classes.mapper;

import com.gymconnect.classes.dto.ClassCreateRequest;
import com.gymconnect.classes.dto.ClassResponse;
import com.gymconnect.classes.entity.Class;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trainer", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    Class toEntity(ClassCreateRequest request);

    default ClassResponse toResponse(Class entity) {
        return ClassResponse.builder()
                .id(entity.getId())
                .days(entity.getDays())
                .price(entity.getPrice())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .imageUrl(entity.getImageUrl())
                .scheduleTime(entity.getScheduleTime())
                .trainerName(entity.getTrainer().getFirstName() + " " + entity.getTrainer().getLastName())
                .capacity(entity.getCapacity())
                .build();
    }

    List<ClassResponse> toResponseList(List<Class> entities);
}
