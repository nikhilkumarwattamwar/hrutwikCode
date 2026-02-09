package com.loanapp.loanManagementSystem.repository.homeloanrepo;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanNpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeLoanNpaRepository extends JpaRepository<HomeLoanNpa, Long> {

    boolean existsByHomeLoan(HomeLoanData homeLoan);

    HomeLoanNpa findByHomeLoan(HomeLoanData homeLoan);
}
