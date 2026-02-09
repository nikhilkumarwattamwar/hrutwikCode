//package com.loanapp.loanManagementSystem.contollers.homeloan;
//
//import com.loanapp.loanManagementSystem.dto.homeloandto.EmiScheduleResponseDto;
//import com.loanapp.loanManagementSystem.dto.homeloandto.GenerateEmiResponseDto;
//import com.loanapp.loanManagementSystem.service.homeloan.service.EmiService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class EmiController {
//
//    private final EmiService emiService;
//
//    public EmiController(EmiService emiService) {
//        this.emiService = emiService;
//    }
//
//
//    @PostMapping("/home-loans/{homeLoanId}/generate-emis")
//    public GenerateEmiResponseDto generateEmis(
//            @PathVariable Long homeLoanId) {
//
//        return emiService.generateEmiSchedule(homeLoanId);
//    }
//
//
//    @GetMapping("/emis/home-loan/{homeLoanId}")
//    public List<EmiScheduleResponseDto> getEmis(
//            @PathVariable Long homeLoanId) {
//
//        return emiService.getEmisByHomeLoan(homeLoanId);
//    }
//
//
//    @PostMapping("/emis/pay/{emiId}")
//    public ResponseEntity<String> payEmi(
//            @PathVariable Long emiId,
//            @RequestParam BigDecimal amount) {
//
//        emiService.payEmi(emiId, amount);
//        return ResponseEntity.ok("EMI Payment Recorded");
//    }
//}
package com.loanapp.loanManagementSystem.contollers.homeloan;
import org.springframework.security.access.prepost.PreAuthorize;
import com.loanapp.loanManagementSystem.dto.homeloandto.EmiScheduleResponseDto;
import com.loanapp.loanManagementSystem.dto.homeloandto.GenerateEmiResponseDto;
import com.loanapp.loanManagementSystem.service.homeloan.service.EmiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmiController {

    private final EmiService emiService;

    public EmiController(EmiService emiService) {
        this.emiService = emiService;
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/home-loans/{homeLoanId}/generate-emis")
    public GenerateEmiResponseDto generateEmis(
            @PathVariable Long homeLoanId) {

        log.info("Received request to generate EMI schedule for HomeLoan ID: {}", homeLoanId);

        GenerateEmiResponseDto response =
                emiService.generateEmiSchedule(homeLoanId);

        log.info("EMI schedule generated successfully for HomeLoan ID: {}. Total EMIs: {}",
                homeLoanId, response.getTotalEmis());

        return response;
    }


    @GetMapping("/emis/home-loan/{homeLoanId}")
    public List<EmiScheduleResponseDto> getEmis(
            @PathVariable Long homeLoanId) {

        log.info("Received request to fetch EMIs for HomeLoan ID: {}", homeLoanId);

        List<EmiScheduleResponseDto> emis =
                emiService.getEmisByHomeLoan(homeLoanId);

        log.info("Fetched {} EMIs for HomeLoan ID: {}", emis.size(), homeLoanId);

        return emis;
    }

    @PostMapping("/emis/pay/{emiId}")
    public ResponseEntity<String> payEmi(
            @PathVariable Long emiId,
            @RequestParam BigDecimal amount) {

        log.info("Received EMI payment request. EMI ID: {}, Amount: {}", emiId, amount);

        emiService.payEmi(emiId, amount);

        log.info("EMI payment recorded successfully. EMI ID: {}", emiId);

        return ResponseEntity.ok("EMI Payment Recorded");
    }
}
