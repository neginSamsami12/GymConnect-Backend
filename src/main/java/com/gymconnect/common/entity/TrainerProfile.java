package com.gymconnect.common.entity;

import com.gymconnect.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "trainer_profiles", schema = "gymconnect")
public class TrainerProfile {
    @Id
    @Column(name = "user_id", nullable = false)
    private UUID id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    @Column(name = "certificate", length = Integer.MAX_VALUE)
    private String certificate;

    @Column(name = "start_activity_year")
    private Integer startActivityYear;

    @Size(max = 100)
    @Column(name = "specialty", length = 100)
    private String specialty;

}