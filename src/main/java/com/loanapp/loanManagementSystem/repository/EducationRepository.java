package com.loanapp.loanManagementSystem.repository;

import com.loanapp.loanManagementSystem.entities.EducationDetails;
import com.loanapp.loanManagementSystem.enums.ExamPassed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EducationRepository extends JpaRepository<EducationDetails,Integer> {

    Optional<List<EducationDetails>> findAllByUserId(UUID userId);

    Optional<EducationDetails> findByUserIdAndExamPassed(UUID userId, ExamPassed examPassed);

}
