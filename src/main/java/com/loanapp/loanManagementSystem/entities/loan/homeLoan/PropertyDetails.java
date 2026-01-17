package com.loanapp.loanManagementSystem.entities.loan.homeLoan;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "property_details")
public class PropertyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    private String propertyType;
    private String propertyAddress;
    private String city;
    private String state;
    private String pincode;
    private BigDecimal marketValue;
    private BigDecimal agreementValue;
    private String constructionStatus;

    private LocalDateTime createdAt;


    @OneToOne
    @JoinColumn(name = "home_loan_id")
    private HomeLoanData homeLoan;

    @OneToOne(mappedBy = "propertyDetails", cascade = CascadeType.ALL)
    private MortgageDetails mortgageDetails;
}

