package com.loanapp.loanManagementSystem.repository;

import com.loanapp.loanManagementSystem.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    Optional<User> findByMobileNumber(String mobileNumber);

    Optional<User> findByIdAndIsActiveTrue(UUID userId);

}
