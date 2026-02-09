package com.loanapp.loanManagementSystem.entities.loan.homeLoan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "property_details")
@Data
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
    @JsonBackReference
    private HomeLoanData homeLoan;

    @OneToOne(mappedBy = "propertyDetails", cascade = CascadeType.ALL)
    private MortgageDetails mortgageDetails;

}

