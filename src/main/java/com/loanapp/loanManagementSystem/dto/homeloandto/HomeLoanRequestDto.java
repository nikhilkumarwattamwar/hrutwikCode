package com.loanapp.loanManagementSystem.dto.homeloandto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Data
public class HomeLoanRequestDto {

    private Long userId;
    private String loanPurpose;
    private BigDecimal requestedAmount;
    private int tenureMonths;
    private int moratoriumMonths;
    private String interestType;
    private  String loanStatus;

    private List<HomeLoanApplicantDto> applicants;
}


