package com.gymconnect.workout.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record WorkoutExerciseCreateRequest(

        @NotBlank
        @Size(max = 200)
        String exerciseName,

        Integer sets,

        Integer reps,

        String description
) {}
