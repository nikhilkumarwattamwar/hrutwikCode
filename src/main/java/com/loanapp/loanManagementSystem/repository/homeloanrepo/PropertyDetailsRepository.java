package com.loanapp.loanManagementSystem.repository.homeloanrepo;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.PropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyDetailsRepository extends JpaRepository<PropertyDetails, Long> {
   Optional<PropertyDetails> findByHomeLoan_HomeLoanId(Long homeLoanId);
}
