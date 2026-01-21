package com.gymconnect.classes.repository;

import com.gymconnect.classes.entity.ClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClassRegistrationRepository extends JpaRepository<ClassRegistration, UUID> {
    List<ClassRegistration> findByUser_Id(UUID userId);
}