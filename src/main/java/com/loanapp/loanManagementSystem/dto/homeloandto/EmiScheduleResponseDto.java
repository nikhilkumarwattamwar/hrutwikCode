package com.loanapp.loanManagementSystem.dto.homeloandto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data

public class EmiScheduleResponseDto {

    private Long emiId;
    private Integer installmentNumber;
    private LocalDate dueDate;
    private BigDecimal emiAmount;
    private BigDecimal paidAmount;
    private String emiStatus;

    // getters & setters
}
