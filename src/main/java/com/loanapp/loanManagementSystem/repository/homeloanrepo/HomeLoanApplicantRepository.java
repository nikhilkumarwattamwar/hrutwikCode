package com.loanapp.loanManagementSystem.repository.homeloanrepo;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeLoanApplicantRepository extends JpaRepository<HomeLoanApplicant, Long> {}