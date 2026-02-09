//package com.loanapp.loanManagementSystem.service.homeloan.service.impl;
//
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanEligibility;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanEligibilityRepository;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
//import com.loanapp.loanManagementSystem.service.homeloan.service.EligibilityService;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class EligibilityServiceImpl implements EligibilityService {
//
//    private static final double MAX_FOIR = 40.0;
//
//    private final HomeLoanRepository homeLoanRepository;
//    private final HomeLoanEligibilityRepository eligibilityRepository;
//
//    public EligibilityServiceImpl(HomeLoanRepository homeLoanRepository,
//                                  HomeLoanEligibilityRepository eligibilityRepository) {
//        this.homeLoanRepository = homeLoanRepository;
//        this.eligibilityRepository = eligibilityRepository;
//    }
//
//    @Override
//    public boolean checkEligibility(Long homeLoanId) {
//
//        HomeLoanData homeLoan = homeLoanRepository.findById(homeLoanId)
//                .orElseThrow(() -> new RuntimeException("Home loan not found"));
//
//        // 1️⃣ Monthly Income
//        double monthlyIncome = calculateMonthlyIncome(homeLoan);
//
//        // 2️⃣ Home Loan EMI (ONLY obligation for now)
//        double homeLoanEmi = calculateHomeLoanEmi(homeLoan);
//
//        // 3️⃣ FOIR calculation
//        double foir = (homeLoanEmi / monthlyIncome) * 100;
//
//        boolean eligible = foir <= MAX_FOIR;
//
//        // 4️⃣ Save eligibility result
//        saveEligibility(homeLoan, foir, eligible);
//
//        return eligible;
//    }
//
//    // ---------------- HELPERS ----------------
//
//    private double calculateMonthlyIncome(HomeLoanData homeLoan) {
//
//        return homeLoan.getApplicants()
//                .stream()
//                .mapToDouble(a -> a.getAnnualIncome().doubleValue())
//                .sum() / 12;
//    }
//
//    private double calculateHomeLoanEmi(HomeLoanData homeLoan) {
//
//        double principal = homeLoan.getRequestedAmount().doubleValue();
//        double annualRate = homeLoan.getInterestRate().doubleValue();
//        int tenureMonths = homeLoan.getTenureMonths() * 12;
//
//        double monthlyRate = annualRate / 12 / 100;
//
//        return (principal * monthlyRate * Math.pow(1 + monthlyRate, tenureMonths)) /
//                (Math.pow(1 + monthlyRate, tenureMonths) - 1);
//    }
//
//    private void saveEligibility(HomeLoanData homeLoan,
//                                 double foir,
//                                 boolean eligible) {
//
//        HomeLoanEligibility eligibility = new HomeLoanEligibility();
//
//        eligibility.setHomeLoan(homeLoan);
//        eligibility.setFoirPercentage(foir);
//        eligibility.setEligible(eligible);
//        eligibility.setCalculatedAt(LocalDateTime.now());
//
//        eligibilityRepository.save(eligibility);
//    }
//}
package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanEligibility;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanEligibilityRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.EligibilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class EligibilityServiceImpl implements EligibilityService {

    private static final double MAX_FOIR = 40.0;

    private final HomeLoanRepository homeLoanRepository;
    private final HomeLoanEligibilityRepository eligibilityRepository;

    public EligibilityServiceImpl(HomeLoanRepository homeLoanRepository,
                                  HomeLoanEligibilityRepository eligibilityRepository) {
        this.homeLoanRepository = homeLoanRepository;
        this.eligibilityRepository = eligibilityRepository;
    }

    @Override
    public boolean checkEligibility(Long homeLoanId) {

        log.info("Starting eligibility check for HomeLoan ID: {}", homeLoanId);

        HomeLoanData homeLoan = homeLoanRepository.findById(homeLoanId)
                .orElseThrow(() -> {
                    log.error("Home loan not found for ID: {}", homeLoanId);
                    return new RuntimeException("Home loan not found");
                });


        double monthlyIncome = calculateMonthlyIncome(homeLoan);
        log.debug("Calculated monthly income: {}", monthlyIncome);


        double homeLoanEmi = calculateHomeLoanEmi(homeLoan);
        log.debug("Calculated home loan EMI: {}", homeLoanEmi);


        double foir = (homeLoanEmi / monthlyIncome) * 100;
        log.info("Calculated FOIR: {}%", foir);

        boolean eligible = foir <= MAX_FOIR;

        log.info("Eligibility result for HomeLoan ID {} → Eligible: {}", homeLoanId, eligible);

        saveEligibility(homeLoan, foir, eligible);

        log.info("Eligibility details saved successfully for HomeLoan ID: {}", homeLoanId);

        return eligible;
    }



    private double calculateMonthlyIncome(HomeLoanData homeLoan) {

        double income = homeLoan.getApplicants()
                .stream()
                .mapToDouble(a -> a.getAnnualIncome().doubleValue())
                .sum() / 12;

        log.debug("Total monthly income from applicants: {}", income);
        return income;
    }

    private double calculateHomeLoanEmi(HomeLoanData homeLoan) {

        double principal = homeLoan.getRequestedAmount().doubleValue();
        double annualRate = homeLoan.getInterestRate().doubleValue();
        int tenureMonths = homeLoan.getTenureMonths() * 12;

        double monthlyRate = annualRate / 12 / 100;

        double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, tenureMonths)) /
                (Math.pow(1 + monthlyRate, tenureMonths) - 1);

        log.debug("EMI calculation → Principal: {}, Rate: {}, Tenure(months): {}, EMI: {}",
                principal, annualRate, tenureMonths, emi);

        return emi;
    }

    private void saveEligibility(HomeLoanData homeLoan,
                                 double foir,
                                 boolean eligible) {

        HomeLoanEligibility eligibility = new HomeLoanEligibility();

        eligibility.setHomeLoan(homeLoan);
        eligibility.setFoirPercentage(foir);
        eligibility.setEligible(eligible);
        eligibility.setCalculatedAt(LocalDateTime.now());

        eligibilityRepository.save(eligibility);

        log.debug("Eligibility entity persisted for HomeLoan ID: {}", homeLoan.getHomeLoanId());
    }
}
