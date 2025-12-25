package com.loanapp.loanManagementSystem.dto.loan;

import com.loanapp.loanManagementSystem.entities.loan.Loan;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeLoanDto extends LoanDto {

    @NotNull(message = "Property address cannot be null")
    @Size(max = 200)
    private String propertyAddress;

    @NotNull(message = "Property value cannot be null")
    @DecimalMin(value = "0.0", message = "property value cannot be negative")
    private Double propertyValue;


}
