package com.loanapp.loanManagementSystem.service.homeloan.service;

import com.loanapp.loanManagementSystem.dto.homeloandto.CloseLoanResponseDto;

public interface LoanClosureService {

    CloseLoanResponseDto closeLoan(Long homeLoanId);
}
