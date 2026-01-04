package com.gymconnect.workout.service;

import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.user.entity.User;
import com.gymconnect.user.repository.UserRepository;
import com.gymconnect.workout.dto.WorkoutCreateRequest;
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
    @Transactional(readOnly = true)
    public ApiResponse create(WorkoutCreateRequest request) {

        User trainer = userRepository.findById(request.trainerId())
                .orElseThrow(() -> new IllegalArgumentException("Trainer not found"));

        User athlete = userRepository.findById(request.athleteId())
                .orElseThrow(() -> new IllegalArgumentException("Athlete not found"));

        // 1) ساخت Workout (بدون setId)
        Workout workout = workoutMapper.toEntity(request);
        workout.setTrainer(trainer);
        workout.setAthlete(athlete);

        workoutRepository.save(workout);

        // 2) ساخت Exercises (بدون setId)
        List<WorkoutExercise> exerciseEntities = new ArrayList<>();

        if (request.exercises() != null && !request.exercises().isEmpty()) {
            for (var exReq : request.exercises()) {
                WorkoutExercise ex = workoutExerciseMapper.toEntity(exReq);
                ex.setWorkout(workout);
                exerciseEntities.add(ex);
            }
            workoutExerciseRepository.saveAll(exerciseEntities);
        }

        // 3) پاسخ
        WorkoutResponse workoutResponse = workoutMapper.toResponse(workout);
        List<WorkoutExerciseResponse> exercisesResponse =
                workoutExerciseMapper.toResponseList(exerciseEntities);

        WorkoutResponse finalResponse = new WorkoutResponse(
                workoutResponse.id(),
                workoutResponse.trainerId(),
                workoutResponse.athleteId(),
                workoutResponse.title(),
                workoutResponse.description(),
                workoutResponse.createdAt(),
                exercisesResponse
        );

        return ApiResponse.success(finalResponse);
    }

    public ApiResponse findAll(UUID trainerId, UUID athleteId) {

        // 1) واکشی workouts با فیلتر اختیاری
        List<Workout> workouts;
        if (trainerId != null) {
            workouts = workoutRepository.findAllByTrainer_Id((trainerId));
        } else if (athleteId != null) {
            workouts = workoutRepository.findAllByAthlete_Id((athleteId));
        } else {
            workouts = workoutRepository.findAll();
        }

        if (workouts.isEmpty()) {
            return ApiResponse.success(Collections.emptyList());
        }

        // 2) واکشی exercises با یک query (به جای N+1)
        List<UUID> workoutIds = workouts.stream()
                .map(Workout::getId)
                .toList();

        List<WorkoutExercise> allExercises =
                workoutExerciseRepository.findAllByWorkout_IdIn((workoutIds));

        Map<UUID, List<WorkoutExercise>> exerciseMap = allExercises.stream()
                .collect(Collectors.groupingBy(ex -> ex.getWorkout().getId()));

        // 3) ساخت WorkoutResponse نهایی (با exercises)
        List<WorkoutResponse> responses = workouts.stream().map(w -> {
            WorkoutResponse base = workoutMapper.toResponse(w);

            List<WorkoutExerciseResponse> exResponses = workoutExerciseMapper
                    .toResponseList(exerciseMap.getOrDefault(w.getId(), List.of()));

            return new WorkoutResponse(
                    base.id(),
                    base.trainerId(),
                    base.athleteId(),
                    base.title(),
                    base.description(),
                    base.createdAt(),
                    exResponses
            );
        }).toList();

        return ApiResponse.success(responses);
    }

}
