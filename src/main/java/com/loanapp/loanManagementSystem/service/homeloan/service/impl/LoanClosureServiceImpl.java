//package com.loanapp.loanManagementSystem.service.homeloan.service.impl;
//
//import com.loanapp.loanManagementSystem.dto.homeloandto.CloseLoanResponseDto;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.MortgageDetails;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.EmiScheduleRepository;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.MortgageDetailsRepository;
//import com.loanapp.loanManagementSystem.service.homeloan.service.LoanClosureService;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@Transactional
//public class LoanClosureServiceImpl implements LoanClosureService {
//
//    private final HomeLoanRepository loanRepo;
//    private final EmiScheduleRepository emiRepo;
//    private final MortgageDetailsRepository mortgageRepo;
//
//    public LoanClosureServiceImpl(HomeLoanRepository loanRepo,
//                                  EmiScheduleRepository emiRepo,
//                                  MortgageDetailsRepository mortgageRepo) {
//        this.loanRepo = loanRepo;
//        this.emiRepo = emiRepo;
//        this.mortgageRepo = mortgageRepo;
//    }
//
//    @Override
//    public CloseLoanResponseDto closeLoan(Long homeLoanId) {
//
//        HomeLoanData loan = loanRepo.findById(homeLoanId)
//                .orElseThrow(() -> new RuntimeException("Loan not found"));
//
//
//        if ("NPA".equalsIgnoreCase(loan.getLoanStatus())) {
//            throw new RuntimeException("NPA loan cannot be closed");
//        }
//
//
//        boolean pendingEmis =
//                emiRepo.existsByHomeLoan_HomeLoanIdAndEmiStatusNot(
//                        homeLoanId, "PAID");
//
//        if (pendingEmis) {
//            throw new RuntimeException("Pending EMIs exist");
//        }
//
//
//        MortgageDetails mortgage =
//                mortgageRepo.findByPropertyDetails_HomeLoan_HomeLoanId(homeLoanId)
//                        .orElseThrow(() -> new RuntimeException("Mortgage not found"));
//
//        if (!Boolean.TRUE.equals(mortgage.getMortgageReleased())) {
//            throw new RuntimeException("Mortgage not released");
//        }
//
//
//        loan.setLoanStatus("CLOSED");
//        loan.setUpdatedAt(LocalDateTime.now());
//
//        loanRepo.save(loan);
//
//        CloseLoanResponseDto response = new CloseLoanResponseDto();
//        response.setHomeLoanId(homeLoanId);
//        response.setLoanStatus("CLOSED");
//        response.setMessage("Loan Closed Successfully");
//        response.setClosedAt(LocalDateTime.now());
//
//        return response;
//    }
//}
//
package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import com.loanapp.loanManagementSystem.dto.homeloandto.CloseLoanResponseDto;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.MortgageDetails;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.EmiScheduleRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.MortgageDetailsRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.LoanClosureService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class LoanClosureServiceImpl implements LoanClosureService {

    private final HomeLoanRepository loanRepo;
    private final EmiScheduleRepository emiRepo;
    private final MortgageDetailsRepository mortgageRepo;

    public LoanClosureServiceImpl(HomeLoanRepository loanRepo,
                                  EmiScheduleRepository emiRepo,
                                  MortgageDetailsRepository mortgageRepo) {
        this.loanRepo = loanRepo;
        this.emiRepo = emiRepo;
        this.mortgageRepo = mortgageRepo;
    }

    @Override
    public CloseLoanResponseDto closeLoan(Long homeLoanId) {

        log.info("Initiating loan closure. HomeLoan ID: {}", homeLoanId);

        HomeLoanData loan = loanRepo.findById(homeLoanId)
                .orElseThrow(() -> {
                    log.error("Loan not found for ID: {}", homeLoanId);
                    return new RuntimeException("Loan not found");
                });

        // ❌ NPA check
        if ("NPA".equalsIgnoreCase(loan.getLoanStatus())) {
            log.warn("Attempt to close NPA loan. HomeLoan ID: {}", homeLoanId);
            throw new RuntimeException("NPA loan cannot be closed");
        }

        // 🔍 EMI pending check
        boolean pendingEmis =
                emiRepo.existsByHomeLoan_HomeLoanIdAndEmiStatusNot(
                        homeLoanId, "PAID");

        log.debug("Pending EMI check result for HomeLoan ID {}: {}",
                homeLoanId, pendingEmis);

        if (pendingEmis) {
            log.warn("Pending EMIs exist. Loan cannot be closed. HomeLoan ID: {}", homeLoanId);
            throw new RuntimeException("Pending EMIs exist");
        }

        // 🔐 Mortgage check
        MortgageDetails mortgage =
                mortgageRepo.findByPropertyDetails_HomeLoan_HomeLoanId(homeLoanId)
                        .orElseThrow(() -> {
                            log.error("Mortgage not found for HomeLoan ID: {}", homeLoanId);
                            return new RuntimeException("Mortgage not found");
                        });

        if (!Boolean.TRUE.equals(mortgage.getMortgageReleased())) {
            log.warn("Mortgage not released. Loan cannot be closed. HomeLoan ID: {}", homeLoanId);
            throw new RuntimeException("Mortgage not released");
        }

        // ✅ Close loan
        loan.setLoanStatus("CLOSED");
        loan.setUpdatedAt(LocalDateTime.now());
        loanRepo.save(loan);

        log.info("Loan closed successfully. HomeLoan ID: {}", homeLoanId);

        CloseLoanResponseDto response = new CloseLoanResponseDto();
        response.setHomeLoanId(homeLoanId);
        response.setLoanStatus("CLOSED");
        response.setMessage("Loan Closed Successfully");
        response.setClosedAt(LocalDateTime.now());

        return response;
    }
}
