package com.loanapp.loanManagementSystem.dto.loan;

import com.loanapp.loanManagementSystem.entities.loan.Loan;
import com.loanapp.loanManagementSystem.enums.BusinessType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessLoanDto extends LoanDto{

    @NotNull(message = "Associated loan is required")
    private Loan loan;


    @NotBlank(message = "Business name is required")
    @Size(max = 255, message = "Business name must not exceed 255 characters")
    private String businessName;

    @NotNull(message = "Business type is required")
    private BusinessType businessType;



    @NotNull(message = "Annual turnover is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Annual turnover cannot be negative")
    @Digits(integer = 15, fraction = 2, message = "Annual turnover format invalid (max 15 digits before decimal)")
    private Double annualTurnover;

    @NotBlank(message = "Business address is required")
    @Size(max = 500)
    private String businessAddress;

}
