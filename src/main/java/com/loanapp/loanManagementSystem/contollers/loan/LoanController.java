package com.loanapp.loanManagementSystem.contollers.loan;

import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.service.loan.LoanService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class LoanController {

    @Autowired
    LoanService service;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user/{userId}/loan")
    public LoanDto createLoan(@PathVariable UUID userId,
                              @RequestBody @Valid LoanDto loanDto) {
        LoanDto createdLoan = service.createLoan(userId, loanDto);
        return createdLoan;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/user/{userId}/loan")
    public List<LoanDto> getLoansByUserId(@PathVariable UUID userId) {
        List<LoanDto> loans = service.getLoansByUserId(userId);
        return loans;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/user/loan/{loanId}")
    public LoanDto getLoanById(@PathVariable UUID loanId) {
        LoanDto loanDto = service.getLoanById(loanId);
        return loanDto;
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/user/loan/{loanId}")
    public LoanDto updateLoan(@PathVariable UUID loanId, @RequestBody @Valid LoanDto loanDto) {
        LoanDto updatedLoan = service.updateLoan(loanId, loanDto);
        return updatedLoan;
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/user/loan/{loanId}")
    public String deleteLoan(@PathVariable UUID loanId) {
        service.deleteLoan(loanId);
        return "Loan deleted successfully";
    }


}
