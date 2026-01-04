package com.gymconnect.workout.repository;

import com.gymconnect.workout.entity.Workout;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {

    @EntityGraph(attributePaths = {"trainer", "athlete"})
    List<Workout> findAll();

    @EntityGraph(attributePaths = {"trainer", "athlete"})
    List<Workout> findAllByTrainer_Id(UUID trainerId);

    @EntityGraph(attributePaths = {"trainer", "athlete"})
    List<Workout> findAllByAthlete_Id(UUID athleteId);
}
