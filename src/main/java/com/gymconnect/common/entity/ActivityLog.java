package com.gymconnect.common.entity;

import com.gymconnect.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "activity_logs", schema = "gymconnect")
public class ActivityLog {
    @Id
    @ColumnDefault("gen_random_uuid()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "action", length = Integer.MAX_VALUE)
    private String action;

    @Size(max = 50)
    @Column(name = "ip_address", length = 50)
    private String ipAddress;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private Instant createdAt;

}