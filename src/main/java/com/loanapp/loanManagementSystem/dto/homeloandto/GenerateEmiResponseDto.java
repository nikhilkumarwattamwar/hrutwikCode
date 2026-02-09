package com.loanapp.loanManagementSystem.dto.homeloandto;


import lombok.Data;

import java.util.List;
@Data
public class GenerateEmiResponseDto {

    private Long homeLoanId;
    private Integer totalEmis;
    private List<EmiScheduleResponseDto> emis;

    // getters & setters
}
