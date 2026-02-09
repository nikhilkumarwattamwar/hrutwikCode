package com.loanapp.loanManagementSystem.contollers.homeloan;


import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanApproval;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanApprovalRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.LoanApprovalService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/home-app")
public class LoanApprovalController {

    private final LoanApprovalService loanApprovalService;

    public LoanApprovalController(LoanApprovalService loanApprovalService) {
        this.loanApprovalService = loanApprovalService;
    }

    @PreAuthorize("hasRole( 'ADMIN')")
    @PostMapping("/{homeLoanId}/approve")
    public ResponseEntity<String> approveLoan(
            @PathVariable Long homeLoanId,
            @RequestParam(required = false) Long approvedBy,
            @RequestParam(required = false) String remarks) {

        String status =
                loanApprovalService.approveLoan(homeLoanId, approvedBy, remarks);

        return ResponseEntity.ok(status);
    }
}
