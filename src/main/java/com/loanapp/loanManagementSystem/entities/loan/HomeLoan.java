package com.loanapp.loanManagementSystem.entities.loan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("HOME")
public class HomeLoan extends Loan {

    private String propertyAddress;
    private Double propertyValue;


}
