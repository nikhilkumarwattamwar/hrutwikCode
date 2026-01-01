package com.loanapp.loanManagementSystem.contollers.admin;

import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.dto.user.UserDto;
import com.loanapp.loanManagementSystem.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService service;

    @GetMapping("/loan")
    public List<LoanDto> getAllLoans() {
        return service.getAllLoan();
    }

    @GetMapping("/loan/{loanId}")
    public LoanDto getLoanByLoanId(@PathVariable UUID loanId) {
        return service.getLoanByLoanId(loanId);
    }

    @PutMapping("/loan/{loanId}/approve")
    public LoanDto apprroveLoan(@PathVariable UUID loanId) {
        return service.approveLoan(loanId);
    }

    @PutMapping("/loan/{loanId}/reject")
    public LoanDto rejectLoan(@PathVariable UUID loanId, @RequestParam String reason) {
        return service.rejectLoan(loanId, reason);
    }

    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return service.getAllUsers();
    }


}
