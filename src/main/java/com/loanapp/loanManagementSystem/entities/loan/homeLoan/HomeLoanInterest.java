package com.loanapp.loanManagementSystem.entities.loan.homeLoan;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "home_loan_interest")
@Data
public class HomeLoanInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "home_loan_id")
    private HomeLoanData homeLoan;

    private Double baseRate;
    private Double riskPremium;
    private Double effectiveRate;
    private BigDecimal loanAmount;
    private Integer tenureMonths;
    private Double totalPayable;
    private LocalDateTime calculatedAt;

    // getters & setters
}

