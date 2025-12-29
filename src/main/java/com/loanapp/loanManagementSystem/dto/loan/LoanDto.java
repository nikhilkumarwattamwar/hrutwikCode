package com.loanapp.loanManagementSystem.dto.loan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.loanapp.loanManagementSystem.enums.LoanType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property = "loanType", include = JsonTypeInfo.As.EXISTING_PROPERTY, visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HomeLoanDto.class,name = "HOME"),
        @JsonSubTypes.Type(value = EducationLoanDto.class,name = "EDUCATION"),
        @JsonSubTypes.Type(value = PersonalLoanDto.class,name = "PERSONAL"),
        @JsonSubTypes.Type(value = BusinessLoanDto.class,name = "BUSINESS"),

})
public class LoanDto {

    private UUID loanId;

    @NotNull(message = "Loan type is required")
    @JsonProperty("loanType")
    private LoanType loanType;

    @NotNull(message = "Loan amount is required")
    private Double loanAmount;

    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "99.99")
    private Double interestRate;

    @NotNull(message = "Tenure is required")
    private Integer tenure;

}
