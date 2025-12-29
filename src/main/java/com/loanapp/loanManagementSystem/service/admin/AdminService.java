package com.loanapp.loanManagementSystem.service.admin;

import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.dto.user.UserDto;
import com.loanapp.loanManagementSystem.entities.user.User;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    List<LoanDto> getAllLoan();

    LoanDto getLoanByLoanId(UUID loanId);

    LoanDto approveLoan(UUID loanId);

    LoanDto rejectLoan(UUID loanId, String reason);

    List<UserDto> getAllUsers();
}
