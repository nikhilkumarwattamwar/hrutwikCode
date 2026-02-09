//package com.loanapp.loanManagementSystem.service.homeloan.service.impl;
//
//import com.loanapp.loanManagementSystem.dto.homeloandto.HomeLoanApplicantDto;
//import com.loanapp.loanManagementSystem.dto.homeloandto.HomeLoanRequestDto;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.EmiSchedule;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanApplicant;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.EmiScheduleRepository;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanApplicantRepository;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
//import com.loanapp.loanManagementSystem.service.homeloan.service.HomeLoanService;
//import com.loanapp.loanManagementSystem.service.homeloan.service.MortgageService;
//import com.loanapp.loanManagementSystem.service.homeloan.utilily.EmiCalculator;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Service
//@Transactional
//public class HomeLoanServiceImpl implements HomeLoanService {
//
//    @Autowired
//    private HomeLoanRepository loanRepo;
//    @Autowired
//    private EmiScheduleRepository emiRepo;
//    @Autowired
//    private HomeLoanApplicantRepository applicantRepo;
//    @Autowired
//    private MortgageService mortgageService;
//
//
//    @Transactional
//    public Long apply(HomeLoanRequestDto dto) {
//
//        // 1️⃣ Create Loan
//        HomeLoanData loan = new HomeLoanData();
//        loan.setUserId(dto.getUserId());
//        loan.setLoanPurpose(dto.getLoanPurpose());
//        loan.setRequestedAmount(dto.getRequestedAmount());
//        loan.setTenureMonths(dto.getTenureMonths());
//        loan.setMoratoriumMonths(dto.getMoratoriumMonths());
//        loan.setInterestType(dto.getInterestType());
//        loan.setLoanStatus(dto.getLoanStatus());
//        loan.setCreatedAt(LocalDateTime.now());
//
//        loanRepo.save(loan);
//
//        Integer primaryCreditScore = null;
//
//        // 2️⃣ Save Applicants
//        for (HomeLoanApplicantDto a : dto.getApplicants()) {
//
//            HomeLoanApplicant applicant = new HomeLoanApplicant();
//            applicant.setUserId(a.getUserId());
//            applicant.setCoApplicantId(a.getCoApplicantId());
//            applicant.setApplicantType(a.getApplicantType());
//            applicant.setFullName(a.getFullName());
//            applicant.setMobileNumber(a.getMobileNumber());
//            applicant.setEmail(a.getEmail());
//            applicant.setAnnualIncome(a.getAnnualIncome());
//            applicant.setCreditScore(a.getCreditScore());
//            applicant.setCreatedAt(LocalDateTime.now());
//            applicant.setHomeLoan(loan);
//
//            applicantRepo.save(applicant);
//
//            // Capture PRIMARY applicant credit score
//            if ("PRIMARY".equalsIgnoreCase(a.getApplicantType())) {
//                primaryCreditScore = a.getCreditScore();
//            }
//        }
//
//        // 3️⃣ Calculate Interest Rate
//        BigDecimal interestRate = calculateInterestRate(
//                primaryCreditScore,
//                dto.getInterestType()
//        );
//
//        // 4️⃣ Save Interest Rate INTO HomeLoanData
//        loan.setInterestRate(interestRate);
//        loan.setUpdatedAt(LocalDateTime.now());
//
//        loanRepo.save(loan);
//
//        return loan.getHomeLoanId();
//    }
//
//    private BigDecimal calculateInterestRate(
//            Integer creditScore,
//            String interestType) {
//
//        if (creditScore == null) {
//            throw new RuntimeException("Primary applicant credit score missing");
//        }
//
//        BigDecimal rate;
//
//        if (creditScore >= 750) {
//            rate = BigDecimal.valueOf(8.5);
//        } else if (creditScore >= 700) {
//            rate = BigDecimal.valueOf(9.0);
//        } else if (creditScore >= 650) {
//            rate = BigDecimal.valueOf(9.5);
//        } else {
//            rate = BigDecimal.valueOf(10.5);
//        }
//
//        if ("FLOATING".equalsIgnoreCase(interestType)) {
//            rate = rate.add(BigDecimal.valueOf(0.25));
//        }
//
//        return rate;
//    }
//
//
//
//    @Override
//    public void approve(Long loanId) {
//
//        HomeLoanData loan = loanRepo.findById(loanId)
//                .orElseThrow(() -> new RuntimeException("Loan not found"));
//
//        loan.setApprovedAmount(
//                loan.getRequestedAmount().multiply(BigDecimal.valueOf(0.8)));
//        loan.setInterestRate(BigDecimal.valueOf(8.5));
//        loan.setLoanStatus("APPROVED");
//
//        BigDecimal emiAmount =
//                EmiCalculator.calculate(
//                        loan.getApprovedAmount(),
//                        loan.getInterestRate(),
//                        loan.getTenureMonths());
//
//        LocalDate start = LocalDate.now()
//                .plusMonths(loan.getMoratoriumMonths());
//
//        for (int i = 1; i <= loan.getTenureMonths(); i++) {
//            EmiSchedule emi = new EmiSchedule();
//            emi.setInstallmentNumber(i);
//            emi.setDueDate(start.plusMonths(i));
//            emi.setEmiAmount(emiAmount);
//            emi.setEmiStatus("PENDING");
//            emi.setHomeLoan(loan);
//            emiRepo.save(emi);
//        }
//
//        loan.setLoanStatus("ACTIVE");
//        loanRepo.save(loan);
//
//        mortgageService.createMortgage(loan.getPropertyDetails().getPropertyId());
//    }
//
//    @Override
//    public void close(Long loanId) {
//
//        HomeLoanData loan = loanRepo.findById(loanId)
//                .orElseThrow(() -> new RuntimeException("Loan not found"));
//
//        boolean allPaid = loan.getEmis()
//                .stream()
//                .allMatch(e -> "PAID".equals(e.getEmiStatus()));
//
//        if (!allPaid)
//            throw new RuntimeException("Pending EMIs exist");
//
//        loan.setLoanStatus("CLOSED");
//        loanRepo.save(loan);
//
//        mortgageService.releaseMortgage(loan.getPropertyDetails().getPropertyId());
//    }
//}
//
package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import com.loanapp.loanManagementSystem.dto.homeloandto.HomeLoanApplicantDto;
import com.loanapp.loanManagementSystem.dto.homeloandto.HomeLoanRequestDto;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.EmiSchedule;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanApplicant;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.EmiScheduleRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanApplicantRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.HomeLoanService;
import com.loanapp.loanManagementSystem.service.homeloan.service.MortgageService;
import com.loanapp.loanManagementSystem.service.homeloan.utilily.EmiCalculator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class HomeLoanServiceImpl implements HomeLoanService {

    @Autowired
    private HomeLoanRepository loanRepo;
    @Autowired
    private EmiScheduleRepository emiRepo;
    @Autowired
    private HomeLoanApplicantRepository applicantRepo;
    @Autowired
    private MortgageService mortgageService;


    @Override
    public Long apply(HomeLoanRequestDto dto) {

        log.info("Applying home loan for User ID: {}", dto.getUserId());


        HomeLoanData loan = new HomeLoanData();
        loan.setUserId(dto.getUserId());
        loan.setLoanPurpose(dto.getLoanPurpose());
        loan.setRequestedAmount(dto.getRequestedAmount());
        loan.setTenureMonths(dto.getTenureMonths());
        loan.setMoratoriumMonths(dto.getMoratoriumMonths());
        loan.setInterestType(dto.getInterestType());
        loan.setLoanStatus(dto.getLoanStatus());
        loan.setCreatedAt(LocalDateTime.now());

        loanRepo.save(loan);

        log.info("Home loan created. Loan ID: {}", loan.getHomeLoanId());

        Integer primaryCreditScore = null;


        for (HomeLoanApplicantDto a : dto.getApplicants()) {

            HomeLoanApplicant applicant = new HomeLoanApplicant();
            applicant.setUserId(a.getUserId());
            applicant.setCoApplicantId(a.getCoApplicantId());
            applicant.setApplicantType(a.getApplicantType());
            applicant.setFullName(a.getFullName());
            applicant.setMobileNumber(a.getMobileNumber());
            applicant.setEmail(a.getEmail());
            applicant.setAnnualIncome(a.getAnnualIncome());
            applicant.setCreditScore(a.getCreditScore());
            applicant.setCreatedAt(LocalDateTime.now());
            applicant.setHomeLoan(loan);

            applicantRepo.save(applicant);

            log.debug("Applicant saved → Name: {}, Type: {}",
                    a.getFullName(), a.getApplicantType());

            if ("PRIMARY".equalsIgnoreCase(a.getApplicantType())) {
                primaryCreditScore = a.getCreditScore();
            }
        }


        BigDecimal interestRate = calculateInterestRate(
                primaryCreditScore,
                dto.getInterestType()
        );

        log.info("Calculated interest rate: {}%", interestRate);


        loan.setInterestRate(interestRate);
        loan.setUpdatedAt(LocalDateTime.now());
        loanRepo.save(loan);

        log.info("Home loan application completed. Loan ID: {}", loan.getHomeLoanId());

        return loan.getHomeLoanId();
    }


    private BigDecimal calculateInterestRate(
            Integer creditScore,
            String interestType) {

        if (creditScore == null) {
            log.error("Primary applicant credit score missing");
            throw new RuntimeException("Primary applicant credit score missing");
        }

        BigDecimal rate;

        if (creditScore >= 750) {
            rate = BigDecimal.valueOf(8.5);
        } else if (creditScore >= 700) {
            rate = BigDecimal.valueOf(9.0);
        } else if (creditScore >= 650) {
            rate = BigDecimal.valueOf(9.5);
        } else {
            rate = BigDecimal.valueOf(10.5);
        }

        if ("FLOATING".equalsIgnoreCase(interestType)) {
            rate = rate.add(BigDecimal.valueOf(0.25));
            log.debug("Floating interest applied. Updated rate: {}", rate);
        }

        return rate;
    }


    @Override
    public void approve(Long loanId) {

        log.info("Approving home loan. Loan ID: {}", loanId);

        HomeLoanData loan = loanRepo.findById(loanId)
                .orElseThrow(() -> {
                    log.error("Loan not found for ID: {}", loanId);
                    return new RuntimeException("Loan not found");
                });

        loan.setApprovedAmount(
                loan.getRequestedAmount().multiply(BigDecimal.valueOf(0.8)));
        loan.setInterestRate(BigDecimal.valueOf(8.5));
        loan.setLoanStatus("APPROVED");

        log.info("Loan approved. Approved amount: {}", loan.getApprovedAmount());

        BigDecimal emiAmount =
                EmiCalculator.calculate(
                        loan.getApprovedAmount(),
                        loan.getInterestRate(),
                        loan.getTenureMonths());

        log.info("Calculated EMI amount: {}", emiAmount);

        LocalDate start = LocalDate.now()
                .plusMonths(loan.getMoratoriumMonths());

        for (int i = 1; i <= loan.getTenureMonths(); i++) {
            EmiSchedule emi = new EmiSchedule();
            emi.setInstallmentNumber(i);
            emi.setDueDate(start.plusMonths(i));
            emi.setEmiAmount(emiAmount);
            emi.setEmiStatus("PENDING");
            emi.setHomeLoan(loan);

            emiRepo.save(emi);

            log.debug("EMI created → Installment: {}, DueDate: {}", i, emi.getDueDate());
        }

        loan.setLoanStatus("ACTIVE");
        loanRepo.save(loan);

        log.info("Loan activated. Creating mortgage for property ID: {}",
                loan.getPropertyDetails().getPropertyId());

        mortgageService.createMortgage(
                loan.getPropertyDetails().getPropertyId());
    }


    @Override
    public void close(Long loanId) {

        log.info("Closing home loan. Loan ID: {}", loanId);

        HomeLoanData loan = loanRepo.findById(loanId)
                .orElseThrow(() -> {
                    log.error("Loan not found for ID: {}", loanId);
                    return new RuntimeException("Loan not found");
                });

        boolean allPaid = loan.getEmis()
                .stream()
                .allMatch(e -> "PAID".equals(e.getEmiStatus()));

        if (!allPaid) {
            log.warn("Cannot close loan. Pending EMIs exist for Loan ID: {}", loanId);
            throw new RuntimeException("Pending EMIs exist");
        }

        loan.setLoanStatus("CLOSED");
        loanRepo.save(loan);

        log.info("Loan closed successfully. Releasing mortgage for property ID: {}",
                loan.getPropertyDetails().getPropertyId());

        mortgageService.releaseMortgage(
                loan.getPropertyDetails().getPropertyId());
    }
}
