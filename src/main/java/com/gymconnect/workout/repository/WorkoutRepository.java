package com.gymconnect.workout.repository;

import com.gymconnect.workout.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {

    @Query("""
    SELECT w
    FROM Workout w
    JOIN FETCH w.trainer t
    JOIN FETCH w.athlete a
    JOIN FETCH w.workoutExercises
    WHERE t.id = :trainerId
    ORDER BY w.createdAt DESC
""")
    List<Workout> findAllByTrainerIdOrderByCreatedAtDesc(UUID trainerId);

    @Query("""
    SELECT w
    FROM Workout w
    JOIN FETCH w.trainer t
    JOIN FETCH w.athlete a
    JOIN FETCH w.workoutExercises
    WHERE a.id = :athleteId
    ORDER BY w.createdAt DESC
""")
    List<Workout> findAllByAthleteIdOrderByCreatedAtDesc(UUID athleteId);

    @Query("""
    SELECT w
    FROM Workout w
    JOIN FETCH w.trainer
    JOIN FETCH w.athlete
    JOIN FETCH w.workoutExercises
    ORDER BY w.createdAt DESC
""")
    List<Workout> findAllOrderByCreatedAtDesc();
}
