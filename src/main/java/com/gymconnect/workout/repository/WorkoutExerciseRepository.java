package com.gymconnect.workout.repository;

import com.gymconnect.workout.entity.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, UUID> {
    List<WorkoutExercise> findAllByWorkout_IdIn(Collection<UUID> workoutIds);
}