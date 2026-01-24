package com.gymconnect.workout.service;

import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.user.entity.User;
import com.gymconnect.user.repository.UserRepository;
import com.gymconnect.workout.dto.WorkoutCreateRequest;
import com.gymconnect.workout.dto.WorkoutExerciseCreateRequest;
import com.gymconnect.workout.dto.WorkoutExerciseResponse;
import com.gymconnect.workout.dto.WorkoutResponse;
import com.gymconnect.workout.entity.Workout;
import com.gymconnect.workout.entity.WorkoutExercise;
import com.gymconnect.workout.mapper.WorkoutExerciseMapper;
import com.gymconnect.workout.mapper.WorkoutMapper;
import com.gymconnect.workout.repository.WorkoutExerciseRepository;
import com.gymconnect.workout.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final UserRepository userRepository;
    private final WorkoutMapper workoutMapper;
    private final WorkoutExerciseMapper workoutExerciseMapper;

    @Override
    @Transactional
    public ApiResponse create(WorkoutCreateRequest request, UUID trainerId) {

        User trainer = userRepository.findById(trainerId)
                .orElseThrow(() -> new IllegalArgumentException("Trainer not found"));

        User athlete = userRepository.findById(request.athleteId())
                .orElseThrow(() -> new IllegalArgumentException("Athlete not found"));

        Workout workout = workoutMapper.toEntity(request);
        workout.setTrainer(trainer);
        workout.setAthlete(athlete);
        workout.setCreatedAt(Instant.now());

        workoutRepository.save(workout);

        List<WorkoutExercise> exerciseEntities = new ArrayList<>();

        if (request.exercises() != null && !request.exercises().isEmpty()) {
            for (WorkoutExerciseCreateRequest exReq : request.exercises()) {
                WorkoutExercise ex = workoutExerciseMapper.toEntity(exReq);
                ex.setWorkout(workout);
                exerciseEntities.add(ex);
            }
            workoutExerciseRepository.saveAll(exerciseEntities);
        }

        return ApiResponse.success(null);
    }

    @Override
    public ApiResponse findAll(UUID trainerId, UUID athleteId) {

        List<Workout> workouts;
        if (trainerId != null) {
            workouts = workoutRepository.findAllByTrainerIdOrderByCreatedAtDesc(trainerId);
        } else if (athleteId != null) {
            workouts = workoutRepository.findAllByAthleteIdOrderByCreatedAtDesc(athleteId);
        } else {
            workouts = workoutRepository.findAllOrderByCreatedAtDesc();
        }

        if (workouts.isEmpty()) {
            return ApiResponse.success(Collections.emptyList());
        }

        List<WorkoutResponse> responses = workouts.stream().map(w -> {
            String athleteName = w.getAthlete().getFirstName() + " " + w.getAthlete().getLastName();
            String trainerName = w.getTrainer().getFirstName() + " " + w.getTrainer().getLastName();

            List<WorkoutExerciseResponse> exResponses = workoutExerciseMapper
                    .toResponseList(w.getWorkoutExercises());

            return WorkoutResponse.builder()
                    .id(w.getId())
                    .athleteName(athleteName)
                    .trainerName(trainerName)
                    .title(w.getTitle())
                    .description(w.getDescription())
                    .exercises(exResponses)
                    .build();
        }).toList();

        return ApiResponse.success(responses);
    }

}
