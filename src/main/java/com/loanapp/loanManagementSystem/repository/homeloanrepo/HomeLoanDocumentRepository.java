package com.loanapp.loanManagementSystem.repository.homeloanrepo;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeLoanDocumentRepository extends JpaRepository<HomeLoanDocument, Long> {
    List<HomeLoanDocument> findByHomeLoan_HomeLoanId(Long homeLoanId);
}
