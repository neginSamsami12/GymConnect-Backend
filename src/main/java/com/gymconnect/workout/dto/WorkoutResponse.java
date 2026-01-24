package com.gymconnect.workout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutResponse {
    private UUID id;
    private String trainerName;
    private String athleteName;
    private String title;
    private String description;
    private List<WorkoutExerciseResponse> exercises;
}
