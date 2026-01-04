package com.gymconnect.classes.repository;

import com.gymconnect.classes.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassRepository extends JpaRepository<Class, UUID> {
}