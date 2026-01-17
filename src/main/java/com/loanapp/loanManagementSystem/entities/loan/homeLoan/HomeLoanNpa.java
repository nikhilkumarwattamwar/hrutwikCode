package com.loanapp.loanManagementSystem.entities.loan.homeLoan;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "home_loan_npa")
public class HomeLoanNpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long npaId;

    private Integer overdueDays;
    private String npaStatus;
    private Boolean writtenOff;
    private LocalDateTime lastUpdated;


    @OneToOne
    @JoinColumn(name = "home_loan_id")
    private HomeLoanData homeLoan;
}
