package com.loanapp.loanManagementSystem.entities.loan;

import com.loanapp.loanManagementSystem.enums.BusinessType;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("BUSINESS")
public class BusinessLoan extends Loan {

    @Column(name = "businessName")
    private String businessName;

    @Enumerated(EnumType.STRING)
    @Column(name = "businessType")
    private BusinessType businessType;

    @Column(name = "businessAddress")
    private String businessAddress;

    @Column(name = "annualTurnover")
    private Double annualTurnover;


}
