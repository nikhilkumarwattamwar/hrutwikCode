package com.loanapp.loanManagementSystem.repository.homeloanrepo;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeLoanRepository extends JpaRepository<HomeLoanData, Long> {}
