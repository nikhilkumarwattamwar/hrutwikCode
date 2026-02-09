//package com.loanapp.loanManagementSystem.contollers.homeloan;
//
//import com.loanapp.loanManagementSystem.dto.homeloandto.MortgageDetailsResponseDto;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.MortgageDetails;
//import com.loanapp.loanManagementSystem.service.homeloan.service.MortgageService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/home-loans")
//public class MortgageController {
//
//    private final MortgageService mortgageService;
//
//    public MortgageController(MortgageService mortgageService) {
//        this.mortgageService = mortgageService;
//    }
//
//    // ✅ STEP 9 – CREATE MORTGAGE
//    @PostMapping("/{homeLoanId}/mortgage")
//    public MortgageDetailsResponseDto createMortgage(@PathVariable Long homeLoanId) {
//        return mortgageService.createMortgage(homeLoanId);
//    }
//
//    // READ
//    @GetMapping("/mortgages/{mortgageId}")
//    public MortgageDetailsResponseDto getMortgage(@PathVariable Long mortgageId) {
//        return mortgageService.getMortgage(mortgageId);
//    }
//
//    // RELEASE (BUSINESS FLOW STEP)
//    @PostMapping("/mortgages/{mortgageId}/release")
//    public MortgageDetailsResponseDto releaseMortgage(@PathVariable Long mortgageId) {
//        return mortgageService.releaseMortgage(mortgageId);
//    }
//
//    // DELETE (ADMIN ONLY)
//    @DeleteMapping("/mortgages/{mortgageId}")
//    public void deleteMortgage(@PathVariable Long mortgageId) {
//        mortgageService.deleteMortgage(mortgageId);
//    }
//}
package com.loanapp.loanManagementSystem.contollers.homeloan;

import com.loanapp.loanManagementSystem.dto.homeloandto.MortgageDetailsResponseDto;
import com.loanapp.loanManagementSystem.service.homeloan.service.MortgageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home-loans")
@Slf4j
public class MortgageController {

    private final MortgageService mortgageService;

    public MortgageController(MortgageService mortgageService) {
        this.mortgageService = mortgageService;
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/{homeLoanId}/mortgage")
    public ResponseEntity<MortgageDetailsResponseDto> createMortgage(
            @PathVariable Long homeLoanId) {

        log.info("Create mortgage request received for HomeLoan ID: {}", homeLoanId);

        MortgageDetailsResponseDto response =
                mortgageService.createMortgage(homeLoanId);

        log.info("Mortgage created successfully for HomeLoan ID: {}", homeLoanId);

        return ResponseEntity
                .status(201)
                .body(response);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/mortgages/{mortgageId}")
    public ResponseEntity<MortgageDetailsResponseDto> getMortgage(
            @PathVariable Long mortgageId) {

        log.info("Fetch mortgage request. Mortgage ID: {}", mortgageId);

        MortgageDetailsResponseDto response =
                mortgageService.getMortgage(mortgageId);

        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/mortgages/{mortgageId}/release")
    public ResponseEntity<MortgageDetailsResponseDto> releaseMortgage(
            @PathVariable Long mortgageId) {

        log.info("Release mortgage request. Mortgage ID: {}", mortgageId);

        MortgageDetailsResponseDto response =
                mortgageService.releaseMortgage(mortgageId);

        log.info("Mortgage released successfully. Mortgage ID: {}", mortgageId);

        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/mortgages/{mortgageId}")
    public ResponseEntity<Void> deleteMortgage(
            @PathVariable Long mortgageId) {

        log.info("Delete mortgage request. Mortgage ID: {}", mortgageId);

        mortgageService.deleteMortgage(mortgageId);

        log.info("Mortgage deleted successfully. Mortgage ID: {}", mortgageId);

        return ResponseEntity.noContent().build();
    }
}
