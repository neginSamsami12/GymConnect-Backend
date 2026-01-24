package com.gymconnect.user.entity;

import com.gymconnect.attendance.entity.AttendanceLog;
import com.gymconnect.classes.entity.Class;
import com.gymconnect.classes.entity.ClassRegistration;
import com.gymconnect.common.entity.*;
import com.gymconnect.common.entity.Role;
import com.gymconnect.user.enums.Gender;
import com.gymconnect.workout.entity.Workout;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "gymconnect")
@DynamicUpdate
public class User {
    @Id
    @ColumnDefault("gen_random_uuid()")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Size(max = 100)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Size(max = 100)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Size(max = 120)
    @Column(name = "email", length = 120)
    private String email;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "password_hash", length = Integer.MAX_VALUE)
    private String passwordHash;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Column(name = "gender", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "address", length = Integer.MAX_VALUE)
    private String address;

    @Size(max = 15)
    @NotNull
    @Column(name = "national_id", nullable = false, length = 15)
    private String nationalId;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("now()")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "image_url", length = Integer.MAX_VALUE)
    private String imageUrl;

    @OneToMany(mappedBy = "user")
    private Set<ActivityLog> activityLogs = new LinkedHashSet<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private AthleteProfile athleteProfile;

    @OneToMany(mappedBy = "user")
    private Set<AttendanceLog> attendanceLogs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<ClassRegistration> classRegistrations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "trainer")
    private Set<Class> classes = new LinkedHashSet<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private EmployeeProfile employeeProfile;

    @OneToMany(mappedBy = "user")
    private Set<Payment> payments = new LinkedHashSet<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private TrainerProfile trainerProfile;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "trainer")
    private Set<Workout> trainerWorkouts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "athlete")
    private Set<Workout> athleteWorkouts = new LinkedHashSet<>();

}