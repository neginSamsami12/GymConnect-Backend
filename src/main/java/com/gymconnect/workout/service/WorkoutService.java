package com.gymconnect.workout.service;

import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.workout.dto.WorkoutCreateRequest;

import java.util.UUID;

public interface WorkoutService {
    ApiResponse create(WorkoutCreateRequest request);
    ApiResponse findAll(UUID trainerId, UUID athleteId);


}
