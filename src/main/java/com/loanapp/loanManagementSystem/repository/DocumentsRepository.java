package com.loanapp.loanManagementSystem.repository;

import com.loanapp.loanManagementSystem.entities.loan.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsRepository extends JpaRepository<Documents,Integer> {
}
