package Devonox.oktaauthentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanOfficerController {

    @PreAuthorize("hasRole('LOAN_OFFICER')")
    @GetMapping("/api/loanofficer/dashboard")
    public String admin() {

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return "Loan Officer Dashboard Accessed :"+username;
    }
}

