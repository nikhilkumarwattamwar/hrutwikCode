//package com.loanapp.loanManagementSystem.contollers.homeloan;
//
//import com.loanapp.loanManagementSystem.dto.homeloandto.HomeLoanRequestDto;
//import com.loanapp.loanManagementSystem.service.homeloan.service.HomeLoanService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//@RestController
//@RequestMapping("/api/home-loans")
//public class HomeLoanController {
//
//    @Autowired
//    private HomeLoanService homeLoanService;
//
//    @PostMapping("/apply")
//    public ResponseEntity<Long> apply(@RequestBody HomeLoanRequestDto dto) {
//        Long loanId = homeLoanService.apply(dto);
//        return ResponseEntity.ok(loanId);
//    }
//
//    @PostMapping("/{loanId}/approve")
//    public ResponseEntity<String> approve(@PathVariable Long loanId) {
//        homeLoanService.approve(loanId);
//        return ResponseEntity.ok("Loan Approved & Activated");
//    }
//
//    @PostMapping("/{loanId}/close")
//    public ResponseEntity<String> close(@PathVariable Long loanId) {
//        homeLoanService.close(loanId);
//        return ResponseEntity.ok("Loan Closed Successfully");
//    }
//}
//
//
package com.loanapp.loanManagementSystem.contollers.homeloan;

import com.loanapp.loanManagementSystem.dto.homeloandto.HomeLoanRequestDto;
import com.loanapp.loanManagementSystem.service.homeloan.service.HomeLoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home-loans")
@Slf4j
public class HomeLoanController {

    @Autowired
    private HomeLoanService homeLoanService;



    @PreAuthorize("hasRole('USER')")
    @PostMapping("/apply")
    public ResponseEntity<Long> apply(@RequestBody HomeLoanRequestDto dto) {

        log.info("Received home loan apply request for User ID: {}", dto.getUserId());

        Long loanId = homeLoanService.apply(dto);

        log.info("Home loan application successful. Loan ID: {}", loanId);

        return ResponseEntity.ok(loanId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{loanId}/approve")
    public ResponseEntity<String> approve(@PathVariable Long loanId) {

        log.info("Received loan approval request. Loan ID: {}", loanId);

        homeLoanService.approve(loanId);

        log.info("Loan approved and activated. Loan ID: {}", loanId);

        return ResponseEntity.ok("Loan Approved & Activated");
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{loanId}/close")
    public ResponseEntity<String> close(@PathVariable Long loanId) {

        log.info("Received loan closure request. Loan ID: {}", loanId);

        homeLoanService.close(loanId);

        log.info("Loan closed successfully. Loan ID: {}", loanId);

        return ResponseEntity.ok("Loan Closed Successfully");
    }
}
