package com.gymconnect.common.entity;

import com.gymconnect.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "athlete_profiles", schema = "gymconnect")
public class AthleteProfile {
    @Id
    @Column(name = "user_id", nullable = false)
    private UUID id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "weight_kg", nullable = false)
    private Integer weightKg;

    @Column(name = "goal", length = Integer.MAX_VALUE)
    private String goal;

    @Column(name = "medical_notes", length = Integer.MAX_VALUE)
    private String medicalNotes;

    @Size(max = 50)
    @Column(name = "membership_type", length = 50)
    private String membershipType;

}