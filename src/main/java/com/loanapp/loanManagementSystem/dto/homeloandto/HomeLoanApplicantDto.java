package com.loanapp.loanManagementSystem.dto.homeloandto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Data
public class HomeLoanApplicantDto {

    private Long userId;
    private Long coApplicantId;
    private String applicantType; // PRIMARY / CO_APPLICANT

    private String fullName;
    private String mobileNumber;
    private String email;
    private BigDecimal annualIncome;
    private Integer creditScore;
}
