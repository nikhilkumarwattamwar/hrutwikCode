package com.loanapp.loanManagementSystem.entities.loan.homeLoan;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "home_loan_approval")
@Data
public class HomeLoanApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long approvalId;

    @OneToOne
    @JoinColumn(name = "home_loan_id", nullable = false)
    private HomeLoanData homeLoan;

    private String approvalStatus;
    private BigDecimal approvedAmount;
    private Long approvedBy;
    private LocalDateTime approvedAt;
    private String remarks;
}
