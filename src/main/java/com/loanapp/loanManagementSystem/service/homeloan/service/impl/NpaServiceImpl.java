package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import com.loanapp.loanManagementSystem.dto.homeloandto.HomeLoanNpaResponseDto;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.EmiSchedule;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanNpa;
import com.loanapp.loanManagementSystem.enums.homeloan.NpaStatus;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.EmiScheduleRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanNpaRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.NpaService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class NpaServiceImpl implements NpaService {

    private final EmiScheduleRepository emiRepo;
    private final HomeLoanNpaRepository npaRepo;
    private final HomeLoanRepository loanRepo;
    private final ModelMapper modelMapper;

    public NpaServiceImpl(EmiScheduleRepository emiRepo,
                          HomeLoanNpaRepository npaRepo,
                          HomeLoanRepository loanRepo,
                          ModelMapper modelMapper) {
        this.emiRepo = emiRepo;
        this.npaRepo = npaRepo;
        this.loanRepo = loanRepo;
        this.modelMapper = modelMapper;
    }

    // 🔁 AUTO NPA CHECK (USED BY SCHEDULER)
    public void checkAndMarkNpa() {

        List<EmiSchedule> pendingEmis =
                emiRepo.findByEmiStatus("PENDING");

        for (EmiSchedule emi : pendingEmis) {

            long overdueDays =
                    java.time.temporal.ChronoUnit.DAYS.between(
                            emi.getDueDate(), LocalDate.now());

            if (overdueDays > 90) {

                HomeLoanData loan = emi.getHomeLoan();

                HomeLoanNpa npa = npaRepo.existsByHomeLoan(loan)
                        ? npaRepo.findByHomeLoan(loan)
                        : new HomeLoanNpa();

                npa.setHomeLoan(loan);
                npa.setOverdueDays((int) overdueDays);
                npa.setNpaStatus(NpaStatus.SUB_STANDARD.name());
                npa.setWrittenOff(false);
                npa.setLastUpdated(LocalDateTime.now());

                npaRepo.save(npa);

                // 🔥 Update loan master
                loan.setLoanStatus("NPA");
                loanRepo.save(loan);
            }
        }
    }

    // READ ALL NPAs
    public List<HomeLoanNpaResponseDto> getAllNpas() {

        return npaRepo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    // READ SINGLE NPA
    public HomeLoanNpaResponseDto getNpa(Long npaId) {

        HomeLoanNpa npa = npaRepo.findById(npaId)
                .orElseThrow(() -> new RuntimeException("NPA not found"));

        return toDto(npa);
    }

    // UPDATE STATUS
    public HomeLoanNpaResponseDto updateStatus(Long npaId, String status) {

        HomeLoanNpa npa = npaRepo.findById(npaId)
                .orElseThrow(() -> new RuntimeException("NPA not found"));

        npa.setNpaStatus(status);
        npa.setLastUpdated(LocalDateTime.now());

        return toDto(npaRepo.save(npa));
    }

    // CURE NPA
    public HomeLoanNpaResponseDto cureNpa(Long npaId) {

        HomeLoanNpa npa = npaRepo.findById(npaId)
                .orElseThrow(() -> new RuntimeException("NPA not found"));

        npa.setNpaStatus(NpaStatus.CURED.name());
        npa.setLastUpdated(LocalDateTime.now());

        HomeLoanData loan = npa.getHomeLoan();
        loan.setLoanStatus("ACTIVE");
        loanRepo.save(loan);

        return toDto(npaRepo.save(npa));
    }

    // ENTITY → DTO
    private HomeLoanNpaResponseDto toDto(HomeLoanNpa npa) {

        HomeLoanNpaResponseDto dto =
                modelMapper.map(npa, HomeLoanNpaResponseDto.class);

        dto.setHomeLoanId(npa.getHomeLoan().getHomeLoanId());
        return dto;
    }
}

