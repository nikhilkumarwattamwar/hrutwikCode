//package com.loanapp.loanManagementSystem.contollers.homeloan;
//
//import com.loanapp.loanManagementSystem.service.homeloan.service.EligibilityService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/home-loans")
//public class EligibilityController {
//
//    private final EligibilityService eligibilityService;
//
//    public EligibilityController(EligibilityService eligibilityService) {
//        this.eligibilityService = eligibilityService;
//    }
//
//    @PostMapping("/{homeLoanId}/eligibility-check")
//    public ResponseEntity<Boolean> checkEligibility(
//            @PathVariable Long homeLoanId) {
//
//        boolean eligible = eligibilityService.checkEligibility(homeLoanId);
//        return ResponseEntity.ok(eligible);
//    }
//}
//
package com.loanapp.loanManagementSystem.contollers.homeloan;

import com.loanapp.loanManagementSystem.service.homeloan.service.EligibilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home-loans")
@Slf4j
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/{homeLoanId}/eligibility-check")
    public ResponseEntity<Boolean> checkEligibility(
            @PathVariable Long homeLoanId) {

        log.info("Received eligibility check request for HomeLoan ID: {}", homeLoanId);

        boolean eligible = eligibilityService.checkEligibility(homeLoanId);

        log.info("Eligibility check completed for HomeLoan ID: {} → Eligible: {}",
                homeLoanId, eligible);

        return ResponseEntity.ok(eligible);
    }
}
