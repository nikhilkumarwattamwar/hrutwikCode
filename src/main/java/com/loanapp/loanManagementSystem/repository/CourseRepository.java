package com.loanapp.loanManagementSystem.repository;

import com.loanapp.loanManagementSystem.entities.CourseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseDetails, Integer> {

    Optional<CourseDetails> findByUserId(UUID userId);

}
