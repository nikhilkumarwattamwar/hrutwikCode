package com.loanapp.loanManagementSystem.entities.loan.homeLoan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "home_loan_documents")
@Data
public class HomeLoanDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    private String documentType;
    private String documentPath;
    private Long uploadedBy;
    private String verificationStatus;
    private Boolean isActive;
    private String originalFileName;   // pan.pdf
    private String contentType;// application/pdf
    private LocalDateTime verifiedAt;
    private Long VerifiedBy;

    private LocalDateTime updatedAt;

    private LocalDateTime uploadedAt;


    @ManyToOne
    @JoinColumn(name = "home_loan_id")
    @JsonIgnore
    private HomeLoanData homeLoan;



}

