package com.loanapp.loanManagementSystem.repository.homeloanrepo;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.EmiSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmiScheduleRepository extends JpaRepository<EmiSchedule, Long> {

    List<EmiSchedule> findByHomeLoan_HomeLoanId(Long homeLoanId);
    List<EmiSchedule> findByEmiStatus(String emiStatus);

    boolean existsByHomeLoan_HomeLoanIdAndEmiStatusNot(Long homeLoanId, String paid);
}
