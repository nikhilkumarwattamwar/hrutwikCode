package com.loanapp.loanManagementSystem.service.homeloan.service;

import com.loanapp.loanManagementSystem.dto.homeloandto.EmiScheduleResponseDto;
import com.loanapp.loanManagementSystem.dto.homeloandto.GenerateEmiResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface EmiService {
    GenerateEmiResponseDto generateEmiSchedule(Long homeLoanId);

    List<EmiScheduleResponseDto> getEmisByHomeLoan(Long homeLoanId);

    void payEmi(Long emiId, BigDecimal amount);
}
