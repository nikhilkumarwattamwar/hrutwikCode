//package com.loanapp.loanManagementSystem.service.homeloan.service.impl;
//
//import com.loanapp.loanManagementSystem.dto.homeloandto.EmiScheduleResponseDto;
//import com.loanapp.loanManagementSystem.dto.homeloandto.GenerateEmiResponseDto;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.EmiSchedule;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
//import com.loanapp.loanManagementSystem.enums.homeloan.EmiStatus;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.EmiScheduleRepository;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
//import com.loanapp.loanManagementSystem.service.homeloan.service.EmiService;
//import jakarta.transaction.Transactional;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@Transactional
//public class EmiServiceImpl implements EmiService {
//
//    private final EmiScheduleRepository emiRepo;
//    private final HomeLoanRepository homeLoanRepo;
//    private final ModelMapper modelMapper;
//
//    public EmiServiceImpl(EmiScheduleRepository emiRepo,
//                          HomeLoanRepository homeLoanRepo,
//                          ModelMapper modelMapper) {
//        this.emiRepo = emiRepo;
//        this.homeLoanRepo = homeLoanRepo;
//        this.modelMapper = modelMapper;
//    }
//
//
//    @Override
//    public GenerateEmiResponseDto generateEmiSchedule(Long homeLoanId) {
//
//        HomeLoanData loan = homeLoanRepo.findById(homeLoanId)
//                .orElseThrow(() -> new RuntimeException("Home loan not found"));
//
//        if (!"APPROVED".equalsIgnoreCase(loan.getLoanStatus())) {
//            throw new RuntimeException("Loan not approved");
//        }
//
//        if (!emiRepo.findByHomeLoan_HomeLoanId(homeLoanId).isEmpty()) {
//            throw new RuntimeException("EMI schedule already generated");
//        }
//
//        BigDecimal principal = loan.getApprovedAmount();
//        BigDecimal annualRate = loan.getInterestRate();
//        int tenureMonths = loan.getTenureMonths();
//
//        BigDecimal emiAmount = calculateEmi(principal, annualRate, tenureMonths);
//
//        LocalDate startDate = LocalDate.now().plusMonths(1);
//
//        for (int i = 1; i <= tenureMonths; i++) {
//
//            EmiSchedule emi = new EmiSchedule();
//            emi.setInstallmentNumber(i);
//            emi.setDueDate(startDate.plusMonths(i - 1));
//            emi.setEmiAmount(emiAmount);
//            emi.setPaidAmount(BigDecimal.ZERO);
//            emi.setEmiStatus(EmiStatus.PENDING.name());
//            emi.setCreatedAt(LocalDateTime.now());
//            emi.setHomeLoan(loan);
//
//            emiRepo.save(emi);
//        }
//
//        List<EmiScheduleResponseDto> dtoList =
//                emiRepo.findByHomeLoan_HomeLoanId(homeLoanId)
//                        .stream()
//                        .map(this::toDto)
//                        .toList();
//
//        GenerateEmiResponseDto response = new GenerateEmiResponseDto();
//        response.setHomeLoanId(homeLoanId);
//        response.setTotalEmis(dtoList.size());
//        response.setEmis(dtoList);
//
//        return response;
//    }
//
//
//    @Override
//    public List<EmiScheduleResponseDto> getEmisByHomeLoan(Long homeLoanId) {
//
//        return emiRepo.findByHomeLoan_HomeLoanId(homeLoanId)
//                .stream()
//                .map(this::toDto)
//                .toList();
//    }
//
//
//    @Override
//    public void payEmi(Long emiId, BigDecimal amount) {
//
//        EmiSchedule emi = emiRepo.findById(emiId)
//                .orElseThrow(() -> new RuntimeException("EMI not found"));
//
//        BigDecimal totalPaid = emi.getPaidAmount().add(amount);
//        emi.setPaidAmount(totalPaid);
//        emi.setPaymentDate(LocalDate.now());
//
//        if (totalPaid.compareTo(emi.getEmiAmount()) >= 0)
//            emi.setEmiStatus(EmiStatus.PAID.name());
//        else
//            emi.setEmiStatus(EmiStatus.PARTIAL.name());
//
//        emiRepo.save(emi);
//    }
//
//
//    private BigDecimal calculateEmi(BigDecimal principal,
//                                    BigDecimal annualRate,
//                                    int tenureMonths) {
//
//        double p = principal.doubleValue();
//        double r = annualRate.doubleValue() / 12 / 100;
//        int n = tenureMonths;
//
//        double emi = (p * r * Math.pow(1 + r, n)) /
//                (Math.pow(1 + r, n) - 1);
//
//        return BigDecimal.valueOf(emi).setScale(2, BigDecimal.ROUND_HALF_UP);
//    }
//
//
//    private EmiScheduleResponseDto toDto(EmiSchedule emi) {
//        return modelMapper.map(emi, EmiScheduleResponseDto.class);
//    }
//}
package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import com.loanapp.loanManagementSystem.dto.homeloandto.EmiScheduleResponseDto;
import com.loanapp.loanManagementSystem.dto.homeloandto.GenerateEmiResponseDto;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.EmiSchedule;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.enums.homeloan.EmiStatus;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.EmiScheduleRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.EmiService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class EmiServiceImpl implements EmiService {

    private final EmiScheduleRepository emiRepo;
    private final HomeLoanRepository homeLoanRepo;
    private final ModelMapper modelMapper;

    public EmiServiceImpl(EmiScheduleRepository emiRepo,
                          HomeLoanRepository homeLoanRepo,
                          ModelMapper modelMapper) {
        this.emiRepo = emiRepo;
        this.homeLoanRepo = homeLoanRepo;
        this.modelMapper = modelMapper;
    }



    @Override
    public GenerateEmiResponseDto generateEmiSchedule(Long homeLoanId) {

        log.info("Generating EMI schedule for HomeLoan ID: {}", homeLoanId);

        HomeLoanData loan = homeLoanRepo.findById(homeLoanId)
                .orElseThrow(() -> {
                    log.error("Home loan not found for ID: {}", homeLoanId);
                    return new RuntimeException("Home loan not found");
                });

        if (!"APPROVED".equalsIgnoreCase(loan.getLoanStatus())) {
            log.warn("Loan ID {} is not approved. Current status: {}",
                    homeLoanId, loan.getLoanStatus());
            throw new RuntimeException("Loan not approved");
        }

        if (!emiRepo.findByHomeLoan_HomeLoanId(homeLoanId).isEmpty()) {
            log.warn("EMI schedule already exists for HomeLoan ID: {}", homeLoanId);
            throw new RuntimeException("EMI schedule already generated");
        }

        BigDecimal principal = loan.getApprovedAmount();
        BigDecimal annualRate = loan.getInterestRate();
        int tenureMonths = loan.getTenureMonths();

        log.debug("EMI input → Principal: {}, Rate: {}, Tenure: {} months",
                principal, annualRate, tenureMonths);

        BigDecimal emiAmount = calculateEmi(principal, annualRate, tenureMonths);

        log.info("Calculated EMI amount: {}", emiAmount);

        LocalDate startDate = LocalDate.now().plusMonths(1);

        for (int i = 1; i <= tenureMonths; i++) {

            EmiSchedule emi = new EmiSchedule();
            emi.setInstallmentNumber(i);
            emi.setDueDate(startDate.plusMonths(i - 1));
            emi.setEmiAmount(emiAmount);
            emi.setPaidAmount(BigDecimal.ZERO);
            emi.setEmiStatus(EmiStatus.PENDING.name());
            emi.setCreatedAt(LocalDateTime.now());
            emi.setHomeLoan(loan);

            emiRepo.save(emi);

            log.debug("Saved EMI → Installment: {}, DueDate: {}", i, emi.getDueDate());
        }

        List<EmiScheduleResponseDto> dtoList =
                emiRepo.findByHomeLoan_HomeLoanId(homeLoanId)
                        .stream()
                        .map(this::toDto)
                        .toList();

        GenerateEmiResponseDto response = new GenerateEmiResponseDto();
        response.setHomeLoanId(homeLoanId);
        response.setTotalEmis(dtoList.size());
        response.setEmis(dtoList);

        log.info("EMI schedule generation completed for HomeLoan ID: {}", homeLoanId);

        return response;
    }



    @Override
    public List<EmiScheduleResponseDto> getEmisByHomeLoan(Long homeLoanId) {

        log.info("Fetching EMI schedule for HomeLoan ID: {}", homeLoanId);

        List<EmiScheduleResponseDto> emis = emiRepo.findByHomeLoan_HomeLoanId(homeLoanId)
                .stream()
                .map(this::toDto)
                .toList();

        log.debug("Total EMIs fetched: {}", emis.size());

        return emis;
    }



    @Override
    public void payEmi(Long emiId, BigDecimal amount) {

        log.info("Processing EMI payment → EMI ID: {}, Amount: {}", emiId, amount);

        EmiSchedule emi = emiRepo.findById(emiId)
                .orElseThrow(() -> {
                    log.error("EMI not found for ID: {}", emiId);
                    return new RuntimeException("EMI not found");
                });

        BigDecimal totalPaid = emi.getPaidAmount().add(amount);
        emi.setPaidAmount(totalPaid);
        emi.setPaymentDate(LocalDate.now());

        if (totalPaid.compareTo(emi.getEmiAmount()) >= 0) {
            emi.setEmiStatus(EmiStatus.PAID.name());
            log.info("EMI ID {} fully paid", emiId);
        } else {
            emi.setEmiStatus(EmiStatus.PARTIAL.name());
            log.info("EMI ID {} partially paid → Paid: {}", emiId, totalPaid);
        }

        emiRepo.save(emi);

        log.debug("EMI payment saved for EMI ID: {}", emiId);
    }



    private BigDecimal calculateEmi(BigDecimal principal,
                                    BigDecimal annualRate,
                                    int tenureMonths) {

        double p = principal.doubleValue();
        double r = annualRate.doubleValue() / 12 / 100;
        int n = tenureMonths;

        double emi = (p * r * Math.pow(1 + r, n)) /
                (Math.pow(1 + r, n) - 1);

        BigDecimal result = BigDecimal.valueOf(emi)
                .setScale(2, BigDecimal.ROUND_HALF_UP);

        log.debug("EMI formula result: {}", result);

        return result;
    }

    private EmiScheduleResponseDto toDto(EmiSchedule emi) {
        return modelMapper.map(emi, EmiScheduleResponseDto.class);
    }
}
