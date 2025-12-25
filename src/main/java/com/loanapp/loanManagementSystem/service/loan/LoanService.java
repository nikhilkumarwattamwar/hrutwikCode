package com.loanapp.loanManagementSystem.service.loan;

import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.enums.LoanType;

import java.util.List;
import java.util.UUID;

public interface LoanService {
    LoanDto createLoan(UUID userId, LoanDto loanDto);

    LoanDto getLoanById(UUID loanId);

    List<LoanDto> getLoansByUserId(UUID userId);

    void deleteLoan(UUID loanId);

    LoanDto updateLoan(UUID loanId, LoanDto loanDto);
}
