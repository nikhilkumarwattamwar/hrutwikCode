package com.loanapp.loanManagementSystem.entities.loan.homeLoan;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "emi_schedule")
@Data
public class EmiSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emiId;

    private Integer installmentNumber;
    private LocalDate dueDate;
    private BigDecimal emiAmount;
    private BigDecimal paidAmount;
    private LocalDate paymentDate;
    private String paymentMode;
    private String emiStatus;
    private BigDecimal lateFee;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "home_loan_id")
    private HomeLoanData homeLoan;
}
