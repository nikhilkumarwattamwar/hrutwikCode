package com.loanapp.loanManagementSystem.repository;

import com.loanapp.loanManagementSystem.entities.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Integer> {

    Optional<PersonalDetails> findByUserId(UUID userId);

}
