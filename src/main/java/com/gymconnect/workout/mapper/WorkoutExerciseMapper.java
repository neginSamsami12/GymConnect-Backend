package com.gymconnect.workout.mapper;

import com.gymconnect.workout.dto.WorkoutExerciseCreateRequest;
import com.gymconnect.workout.dto.WorkoutExerciseResponse;
import com.gymconnect.workout.entity.WorkoutExercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutExerciseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workout", ignore = true)
    WorkoutExercise toEntity(WorkoutExerciseCreateRequest request);

    WorkoutExerciseResponse toResponse(WorkoutExercise entity);

    List<WorkoutExerciseResponse> toResponseList(List<WorkoutExercise> entities);
}
