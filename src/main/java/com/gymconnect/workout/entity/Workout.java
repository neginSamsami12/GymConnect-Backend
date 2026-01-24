package com.gymconnect.workout.entity;

import com.gymconnect.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "workouts", schema = "gymconnect")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private User trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "athlete_id")
    private User athlete;

    @Size(max = 200)
    @NotNull
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "workout")
    @OrderBy("orderIndex ASC")
    private List<WorkoutExercise> workoutExercises = new ArrayList<>();

}