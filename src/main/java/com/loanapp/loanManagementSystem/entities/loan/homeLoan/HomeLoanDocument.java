package com.loanapp.loanManagementSystem.entities.loan.homeLoan;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "home_loan_documents")
public class HomeLoanDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    private String documentType;
    private String documentPath;
    private Long uploadedBy;
    private String verificationStatus;
    private Boolean isActive;

    private LocalDateTime uploadedAt;


    @ManyToOne
    @JoinColumn(name = "home_loan_id")
    private HomeLoanData homeLoan;
}

