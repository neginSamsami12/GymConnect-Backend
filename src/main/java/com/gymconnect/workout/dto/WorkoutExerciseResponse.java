package com.gymconnect.workout.dto;

import java.util.UUID;

public record WorkoutExerciseResponse(
        Integer index,
        String exerciseName,
        Integer sets,
        Integer reps,
        String description
) {}
