package com.loanapp.loanManagementSystem.contollers.homeloan;

import com.loanapp.loanManagementSystem.dto.homeloandto.CloseLoanResponseDto;
import com.loanapp.loanManagementSystem.service.homeloan.service.LoanClosureService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home-closure")
public class LoanClosureController {

    private final LoanClosureService loanClosureService;

    public LoanClosureController(LoanClosureService loanClosureService) {
        this.loanClosureService = loanClosureService;
    }

    @PreAuthorize("hasRole('USER', 'ADMIN')")
    @PostMapping("/{homeLoanId}/close")
    public CloseLoanResponseDto closeLoan(
            @PathVariable Long homeLoanId) {

        return loanClosureService.closeLoan(homeLoanId);
    }
}

