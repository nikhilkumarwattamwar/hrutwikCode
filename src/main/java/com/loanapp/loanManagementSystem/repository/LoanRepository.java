package com.loanapp.loanManagementSystem.repository;

import com.loanapp.loanManagementSystem.entities.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
    Optional<List<Loan>>  findByUserId(UUID id);

   Optional<Loan> findByLoanIdAndIsActiveTrue(UUID loanId);

}
