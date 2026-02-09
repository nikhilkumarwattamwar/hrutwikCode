package com.loanapp.loanManagementSystem.dto.homeloandto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HomeLoanNpaResponseDto {

    private Long npaId;
    private Long homeLoanId;
    private Integer overdueDays;
    private String npaStatus;
    private Boolean writtenOff;
    private LocalDateTime lastUpdated;
}

