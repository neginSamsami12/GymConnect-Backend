package com.gymconnect.classes.repository;

import com.gymconnect.classes.entity.Class;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClassRepository extends JpaRepository<Class, UUID> {

//    @EntityGraph(attributePaths = {"trainer"})
//    List<Class> findAll();

    @EntityGraph(attributePaths = {"trainer"})
    List<Class> findAllByTrainer_Id(UUID trainerId);
}