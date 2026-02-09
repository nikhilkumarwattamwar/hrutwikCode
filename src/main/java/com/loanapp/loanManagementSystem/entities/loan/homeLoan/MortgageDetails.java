package com.loanapp.loanManagementSystem.entities.loan.homeLoan;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "mortgage_details")
@Data
public class MortgageDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mortgageId;

    private LocalDate mortgageStartDate;
    private Boolean mortgageReleased;
    private LocalDate mortgageReleaseDate;
    private LocalDateTime createdAt;


    @OneToOne
    @JoinColumn(name = "property_id")
    private PropertyDetails propertyDetails;
}

