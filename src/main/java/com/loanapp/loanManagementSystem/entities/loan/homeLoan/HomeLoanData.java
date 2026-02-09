package com.loanapp.loanManagementSystem.entities.loan.homeLoan;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "home_loan_data")
@Data
public class HomeLoanData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long homeLoanId;

    @Column(nullable = false)
    private Long userId;
    private String loanPurpose;
    private BigDecimal requestedAmount;
    private BigDecimal approvedAmount;
    private BigDecimal interestRate;
    private String interestType;
    private Integer tenureMonths;
    private Integer moratoriumMonths;
    private String loanStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "homeLoan", cascade = CascadeType.ALL)
    private List<HomeLoanApplicant> applicants;

    @OneToMany(mappedBy = "homeLoan", cascade = CascadeType.ALL)
    private List<EmiSchedule> emis;

    @OneToMany(mappedBy = "homeLoan", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HomeLoanDocument> documents;

    @OneToOne(mappedBy = "homeLoan", cascade = CascadeType.ALL)
    @JsonManagedReference
    private PropertyDetails propertyDetails;

    @OneToOne(mappedBy = "homeLoan", cascade = CascadeType.ALL)
    private HomeLoanNpa npa;
}

