//package com.loanapp.loanManagementSystem.contollers.homeloan;
//
//import com.loanapp.loanManagementSystem.service.homeloan.service.InterestCalculationService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/home-loans")
//public class InterestCalculationController {
//
//    private final InterestCalculationService interestService;
//
//    public InterestCalculationController(InterestCalculationService interestService) {
//        this.interestService = interestService;
//    }
//
//    @PostMapping("/{homeLoanId}/interest-calculate")
//    public ResponseEntity<Double> calculateInterest(
//            @PathVariable Long homeLoanId) {
//
//        double effectiveRate = interestService.calculateInterest(homeLoanId);
//        return ResponseEntity.ok(effectiveRate);
//    }
//}
package com.loanapp.loanManagementSystem.contollers.homeloan;

import com.loanapp.loanManagementSystem.service.homeloan.service.InterestCalculationService;
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
public class InterestCalculationController {

    private final InterestCalculationService interestService;

    public InterestCalculationController(InterestCalculationService interestService) {
        this.interestService = interestService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/{homeLoanId}/interest-calculate")
    public ResponseEntity<Double> calculateInterest(
            @PathVariable Long homeLoanId) {

        log.info("Received interest calculation request for HomeLoan ID: {}", homeLoanId);

        double effectiveRate = interestService.calculateInterest(homeLoanId);

        log.info("Interest calculation completed for HomeLoan ID: {} → Effective Rate: {}",
                homeLoanId, effectiveRate);

        return ResponseEntity.ok(effectiveRate);
    }
}
