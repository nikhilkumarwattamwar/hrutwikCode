package com.loanapp.loanManagementSystem.repository.homeloanrepo;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.MortgageDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MortgageDetailsRepository extends JpaRepository<MortgageDetails, Long> {
   Optional<MortgageDetails> findByPropertyDetails_HomeLoan_HomeLoanId(Long homeLoanId);
}