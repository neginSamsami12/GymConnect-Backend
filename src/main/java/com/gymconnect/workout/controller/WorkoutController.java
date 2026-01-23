package com.gymconnect.workout.controller;

import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.workout.dto.WorkoutCreateRequest;
import com.gymconnect.workout.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid WorkoutCreateRequest request) {
        ApiResponse apiResponse = workoutService.create(request, UUID.fromString("5ac3d10e-4868-4c43-957e-8fc3d308d5c1"));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    // نمونه‌ها:
    // GET /api/workouts
    // GET /api/workouts?trainerId=...
    // GET /api/workouts?athleteId=...
    @GetMapping
    public ResponseEntity<ApiResponse> findAll(
            @RequestParam(value = "trainerId", required = false) UUID trainerId,
            @RequestParam(value = "athleteId", required = false) UUID athleteId
    ) {
        ApiResponse apiResponse = workoutService.findAll(trainerId, athleteId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
