package com.gymconnect.workout.mapper;

import com.gymconnect.workout.dto.WorkoutCreateRequest;
import com.gymconnect.workout.dto.WorkoutResponse;
import com.gymconnect.workout.entity.Workout;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trainer", ignore = true)
    @Mapping(target = "athlete", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Workout toEntity(WorkoutCreateRequest request);

    @Mapping(target = "trainerId", source = "trainer.id")
    @Mapping(target = "athleteId", source = "athlete.id")
    @Mapping(target = "exercises", ignore = true)
    WorkoutResponse toResponse(Workout entity);
}
