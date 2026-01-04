package com.gymconnect.workout.dto;

import java.util.UUID;

public record WorkoutExerciseResponse(
        UUID id,
        String exerciseName,
        Integer sets,
        Integer reps,
        String description
) {}
