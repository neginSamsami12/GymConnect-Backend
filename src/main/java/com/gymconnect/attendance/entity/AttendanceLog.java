package com.gymconnect.attendance.entity;

import com.gymconnect.classes.entity.Class;
import com.gymconnect.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "attendance_logs", schema = "gymconnect")
public class AttendanceLog {
    @Id
    @ColumnDefault("gen_random_uuid()")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ColumnDefault("now()")
    @Column(name = "check_in")
    private Instant checkIn;

    @Column(name = "check_out")
    private Instant checkOut;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_id", nullable = false)
    private Class classField;

}