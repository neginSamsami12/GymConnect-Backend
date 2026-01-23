package com.gymconnect.workout.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record WorkoutCreateRequest(

        @NotNull
        UUID athleteId,

        @NotNull
        @Size(max = 200)
        String title,

        String description,

        @Valid
        List<WorkoutExerciseCreateRequest> exercises
) {}
