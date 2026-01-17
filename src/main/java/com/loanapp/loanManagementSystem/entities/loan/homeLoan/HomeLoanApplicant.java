package com.loanapp.loanManagementSystem.entities.loan.homeLoan;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "home_loan_applicant")
public class HomeLoanApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long coApplicantId;

    private String applicantType;
    private String fullName;
    private String mobileNumber;
    private String email;
    private BigDecimal annualIncome;
    private Integer creditScore;

    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "home_loan_id")
    private HomeLoanData homeLoan;
}

