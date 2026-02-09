package com.loanapp.loanManagementSystem.dto.homeloandto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CloseLoanResponseDto {

    private Long homeLoanId;
    private String loanStatus;
    private String message;
    private LocalDateTime closedAt;
}