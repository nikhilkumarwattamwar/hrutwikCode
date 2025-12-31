package com.loanapp.loanManagementSystem.entities.loan;

import jakarta.persistence.*;
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
@DiscriminatorValue("PERSONAL")
public class PersonalLoan extends Loan {
    private String purpose;
    private Integer monthlyIncome;
    private Boolean salaried;
}
