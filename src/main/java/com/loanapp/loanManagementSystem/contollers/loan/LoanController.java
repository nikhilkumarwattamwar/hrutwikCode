package com.loanapp.loanManagementSystem.contollers.loan;

import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.service.loan.LoanService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class LoanController {

    private static final Logger log = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    LoanService service;

    @PostMapping("/user/{userId}/loan")
    public LoanDto createLoan(@PathVariable UUID userId,
                              @RequestBody @Valid LoanDto loanDto) {
        log.info("Creating loan for userId: {}", userId);
        LoanDto createdLoan = service.createLoan(userId, loanDto);

        log.info("Loan created with id: {}", createdLoan.getLoanId());
        return createdLoan;
    }

    @GetMapping("/user/{userId}/loan")
    public List<LoanDto> getLoansByUserId(@PathVariable UUID userId) {
        log.info("Fetching all active loans for userId: {}", userId);
        List<LoanDto> loans = service.getLoansByUserId(userId);
        log.info("Total loans found: {}", loans.size());
        return loans;
    }


    @GetMapping("/user/loan/{loanId}")
    public LoanDto getLoanById(@PathVariable UUID loanId) {
        log.info("Fetching loan by id: {}", loanId);
        LoanDto loanDto = service.getLoanById(loanId);
        log.info("Loan fetched successfully: {}", loanId);
        return loanDto;
    }

    @PutMapping("/user/loan/{loanId}")
    public LoanDto updateLoan(@PathVariable UUID loanId, @RequestBody @Valid LoanDto loanDto) {

        log.info("Updating loan with id: {}", loanId);
        LoanDto updatedLoan = service.updateLoan(loanId, loanDto);
        log.info("Loan updated successfully: {}", loanId);
        return updatedLoan;
    }

    @DeleteMapping("/user/loan/{loanId}")
    public String deleteLoan(@PathVariable UUID loanId) {
        log.warn("Soft deleting loan with id: {}", loanId);
        service.deleteLoan(loanId);
        log.info("Loan soft deleted: {}", loanId);
        return "Loan deleted successfully";
    }


}
