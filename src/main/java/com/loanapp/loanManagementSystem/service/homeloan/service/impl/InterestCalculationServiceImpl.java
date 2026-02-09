//package com.loanapp.loanManagementSystem.service.homeloan.service.impl;
//
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanInterest;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanInterestRepository;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
//import com.loanapp.loanManagementSystem.service.homeloan.service.InterestCalculationService;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class InterestCalculationServiceImpl implements InterestCalculationService {
//
//    private static final double BASE_RATE = 8.0;
//
//    private final HomeLoanRepository homeLoanRepository;
//    private final HomeLoanInterestRepository interestRepository;
//
//    public InterestCalculationServiceImpl(HomeLoanRepository homeLoanRepository,
//                                          HomeLoanInterestRepository interestRepository) {
//        this.homeLoanRepository = homeLoanRepository;
//        this.interestRepository = interestRepository;
//    }
//
//    @Override
//    public double calculateInterest(Long homeLoanId) {
//
//        HomeLoanData homeLoan = homeLoanRepository.findById(homeLoanId)
//                .orElseThrow(() -> new RuntimeException("Home loan not found"));
//
//        // 1️⃣ Base Rate
//        double baseRate = BASE_RATE;
//
//        // 2️⃣ Credit Score (Primary Applicant)
//        int creditScore = homeLoan.getApplicants().get(0).getCreditScore();
//
//        // 3️⃣ Risk Premium
//        double riskPremium = calculateRiskPremium(creditScore);
//
//        // 4️⃣ Effective Rate
//        double effectiveRate = baseRate + riskPremium;
//
//        // 5️⃣ EMI Calculation
//        double emi = calculateEmi(
//                homeLoan.getRequestedAmount().doubleValue(),
//                effectiveRate,
//                homeLoan.getTenureMonths()
//        );
//
//        // 6️⃣ Total Payable
//        double totalPayable = emi * homeLoan.getTenureMonths();
//
//        // 7️⃣ Save Interest Details
//        saveInterestDetails(homeLoan, baseRate, riskPremium,
//                effectiveRate, totalPayable);
//
//        return effectiveRate;
//    }
//    private double calculateRiskPremium(int creditScore) {
//
//        if (creditScore >= 750) {
//            return 0.5;
//        } else if (creditScore >= 700) {
//            return 1.0;
//        } else if (creditScore >= 650) {
//            return 1.5;
//        } else {
//            return 2.0;
//        }
//    }
//
//    private double calculateEmi(double principal,
//                                double annualRate,
//                                int tenureMonths) {
//
//        double monthlyRate = annualRate / 12 / 100;
//
//        return (principal * monthlyRate * Math.pow(1 + monthlyRate, tenureMonths)) /
//                (Math.pow(1 + monthlyRate, tenureMonths) - 1);
//    }
//    private void saveInterestDetails(HomeLoanData homeLoan,
//                                     double baseRate,
//                                     double riskPremium,
//                                     double effectiveRate,
//                                     double totalPayable) {
//
//        HomeLoanInterest interest = new HomeLoanInterest();
//
//        interest.setHomeLoan(homeLoan);
//        interest.setBaseRate(baseRate);
//        interest.setRiskPremium(riskPremium);
//        interest.setEffectiveRate(effectiveRate);
//        interest.setLoanAmount(homeLoan.getRequestedAmount());
//        interest.setTenureMonths(homeLoan.getTenureMonths());
//        interest.setTotalPayable(totalPayable);
//        interest.setCalculatedAt(LocalDateTime.now());
//
//        interestRepository.save(interest);
//    }
//
//}
//
package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanInterest;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanInterestRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.InterestCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class InterestCalculationServiceImpl implements InterestCalculationService {

    private static final double BASE_RATE = 8.0;

    private final HomeLoanRepository homeLoanRepository;
    private final HomeLoanInterestRepository interestRepository;

    public InterestCalculationServiceImpl(HomeLoanRepository homeLoanRepository,
                                          HomeLoanInterestRepository interestRepository) {
        this.homeLoanRepository = homeLoanRepository;
        this.interestRepository = interestRepository;
    }

    @Override
    public double calculateInterest(Long homeLoanId) {

        log.info("Starting interest calculation for HomeLoan ID: {}", homeLoanId);

        HomeLoanData homeLoan = homeLoanRepository.findById(homeLoanId)
                .orElseThrow(() -> {
                    log.error("Home loan not found for ID: {}", homeLoanId);
                    return new RuntimeException("Home loan not found");
                });


        double baseRate = BASE_RATE;
        log.debug("Base interest rate: {}", baseRate);


        if (homeLoan.getApplicants().isEmpty()) {
            log.error("No applicants found for HomeLoan ID: {}", homeLoanId);
            throw new RuntimeException("No applicants found");
        }

        int creditScore = homeLoan.getApplicants().get(0).getCreditScore();
        log.debug("Primary applicant credit score: {}", creditScore);


        double riskPremium = calculateRiskPremium(creditScore);
        log.debug("Calculated risk premium: {}", riskPremium);


        double effectiveRate = baseRate + riskPremium;
        log.info("Effective interest rate calculated: {}", effectiveRate);


        double emi = calculateEmi(
                homeLoan.getRequestedAmount().doubleValue(),
                effectiveRate,
                homeLoan.getTenureMonths()
        );
        log.debug("Calculated EMI: {}", emi);


        double totalPayable = emi * homeLoan.getTenureMonths();
        log.info("Total payable amount over tenure: {}", totalPayable);


        saveInterestDetails(
                homeLoan,
                baseRate,
                riskPremium,
                effectiveRate,
                totalPayable
        );

        log.info("Interest calculation completed for HomeLoan ID: {}", homeLoanId);

        return effectiveRate;
    }


    private double calculateRiskPremium(int creditScore) {

        if (creditScore >= 750) {
            return 0.5;
        } else if (creditScore >= 700) {
            return 1.0;
        } else if (creditScore >= 650) {
            return 1.5;
        } else {
            log.warn("Low credit score detected: {}", creditScore);
            return 2.0;
        }
    }


    private double calculateEmi(double principal,
                                double annualRate,
                                int tenureMonths) {

        double monthlyRate = annualRate / 12 / 100;

        double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, tenureMonths)) /
                (Math.pow(1 + monthlyRate, tenureMonths) - 1);

        log.debug("EMI formula → Principal: {}, Rate: {}, Tenure: {}, EMI: {}",
                principal, annualRate, tenureMonths, emi);

        return emi;
    }


    private void saveInterestDetails(HomeLoanData homeLoan,
                                     double baseRate,
                                     double riskPremium,
                                     double effectiveRate,
                                     double totalPayable) {

        HomeLoanInterest interest = new HomeLoanInterest();

        interest.setHomeLoan(homeLoan);
        interest.setBaseRate(baseRate);
        interest.setRiskPremium(riskPremium);
        interest.setEffectiveRate(effectiveRate);
        interest.setLoanAmount(homeLoan.getRequestedAmount());
        interest.setTenureMonths(homeLoan.getTenureMonths());
        interest.setTotalPayable(totalPayable);
        interest.setCalculatedAt(LocalDateTime.now());

        interestRepository.save(interest);

        log.debug("Interest details saved for HomeLoan ID: {}",
                homeLoan.getHomeLoanId());
    }
}
