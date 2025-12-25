package com.loanapp.loanManagementSystem.dto.loan;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalLoanDto extends LoanDto {

    @NotBlank(message = "Purpose of the loan is required")
    @Size(min = 5, max = 250, message = "Purpose must be between 5 and 250 characters")
    private String purpose;

    @NotNull(message = "Monthly income is required")
    @Min(value = 0, message = "Monthly income cannot be negative")
    private Integer monthlyIncome;

    @NotNull(message = "Please specify if the user is salaried")
    private Boolean salaried;

}
