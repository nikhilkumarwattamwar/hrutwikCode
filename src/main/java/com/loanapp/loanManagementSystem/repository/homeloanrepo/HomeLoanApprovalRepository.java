package com.loanapp.loanManagementSystem.repository.homeloanrepo;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanApproval;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeLoanApprovalRepository extends JpaRepository<HomeLoanApproval, Long> {

    boolean existsByHomeLoan(HomeLoanData homeLoan);
}
