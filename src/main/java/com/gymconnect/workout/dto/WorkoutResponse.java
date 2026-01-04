package com.gymconnect.workout.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record WorkoutResponse(
        UUID id,
        UUID trainerId,
        UUID athleteId,
        String title,
        String description,
        Instant createdAt,
        List<WorkoutExerciseResponse> exercises
) {}
