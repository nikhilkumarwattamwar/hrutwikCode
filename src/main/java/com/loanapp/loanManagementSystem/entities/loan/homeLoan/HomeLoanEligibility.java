package com.loanapp.loanManagementSystem.entities.loan.homeLoan;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "home_loan_eligibility")
@Data
public class HomeLoanEligibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "home_loan_id")
    private HomeLoanData homeLoan;

    private Double foirPercentage;
    private Boolean eligible;
    private LocalDateTime calculatedAt;

    // getters & setters
}

