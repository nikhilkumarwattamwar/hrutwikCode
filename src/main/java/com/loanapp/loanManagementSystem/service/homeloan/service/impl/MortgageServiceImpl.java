//package com.loanapp.loanManagementSystem.service.homeloan.service.impl;
//
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.MortgageDetails;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.PropertyDetails;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.MortgageDetailsRepository;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.PropertyDetailsRepository;
//import com.loanapp.loanManagementSystem.service.homeloan.service.MortgageService;
//import jakarta.transaction.Transactional;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//import com.loanapp.loanManagementSystem.dto.homeloandto.MortgageDetailsResponseDto;
//
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Service
//@Transactional
//public class MortgageServiceImpl implements MortgageService {
//
//    private final MortgageDetailsRepository mortgageRepo;
//    private final PropertyDetailsRepository propertyRepo;
//    private final ModelMapper modelMapper;
//
//    public MortgageServiceImpl(MortgageDetailsRepository mortgageRepo,
//                               PropertyDetailsRepository propertyRepo,
//                               ModelMapper modelMapper) {
//        this.mortgageRepo = mortgageRepo;
//        this.propertyRepo = propertyRepo;
//        this.modelMapper = modelMapper;
//    }
//
//    // 1️⃣ CREATE MORTGAGE
//    @Override
//    public MortgageDetailsResponseDto createMortgage(Long homeLoanId) {
//
//        PropertyDetails property = propertyRepo.findByHomeLoan_HomeLoanId(homeLoanId)
//                .orElseThrow(() -> new RuntimeException("Property not found for loan"));
//
//        if (property.getMortgageDetails() != null) {
//            throw new RuntimeException("Mortgage already exists");
//        }
//
//        MortgageDetails mortgage = new MortgageDetails();
//        mortgage.setPropertyDetails(property);
//        mortgage.setMortgageStartDate(LocalDate.now());
//        mortgage.setMortgageReleased(false);
//        mortgage.setCreatedAt(LocalDateTime.now());
//
//        MortgageDetails saved = mortgageRepo.save(mortgage);
//
//        return mapToDto(saved);
//    }
//
//    // 2️⃣ READ MORTGAGE
//    @Override
//    public MortgageDetailsResponseDto getMortgage(Long mortgageId) {
//
//        MortgageDetails mortgage = mortgageRepo.findById(mortgageId)
//                .orElseThrow(() -> new RuntimeException("Mortgage not found"));
//
//        return mapToDto(mortgage);
//    }
//
//    // 3️⃣ RELEASE MORTGAGE
//    @Override
//    public MortgageDetailsResponseDto releaseMortgage(Long mortgageId) {
//
//        MortgageDetails mortgage = mortgageRepo.findById(mortgageId)
//                .orElseThrow(() -> new RuntimeException("Mortgage not found"));
//
//        if (Boolean.TRUE.equals(mortgage.getMortgageReleased())) {
//            throw new RuntimeException("Mortgage already released");
//        }
//
//        mortgage.setMortgageReleased(true);
//        mortgage.setMortgageReleaseDate(LocalDate.now());
//
//        MortgageDetails updated = mortgageRepo.save(mortgage);
//
//        return mapToDto(updated);
//    }
//
//    // 4️⃣ DELETE
//    @Override
//    public void deleteMortgage(Long mortgageId) {
//
//        MortgageDetails mortgage = mortgageRepo.findById(mortgageId)
//                .orElseThrow(() -> new RuntimeException("Mortgage not found"));
//
//        mortgageRepo.delete(mortgage);
//    }
//
//    // 🔁 ENTITY → DTO MAPPING
//    private MortgageDetailsResponseDto mapToDto(MortgageDetails mortgage) {
//
//        MortgageDetailsResponseDto dto =
//                modelMapper.map(mortgage, MortgageDetailsResponseDto.class);
//
//        // manual mapping for nested object
//        if (mortgage.getPropertyDetails() != null) {
//            dto.setPropertyId(mortgage.getPropertyDetails().getPropertyId());
//        }
//
//        return dto;
//    }
//}
package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.MortgageDetails;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.PropertyDetails;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.MortgageDetailsRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.PropertyDetailsRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.MortgageService;
import com.loanapp.loanManagementSystem.dto.homeloandto.MortgageDetailsResponseDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class MortgageServiceImpl implements MortgageService {

    private final MortgageDetailsRepository mortgageRepo;
    private final PropertyDetailsRepository propertyRepo;
    private final ModelMapper modelMapper;

    public MortgageServiceImpl(MortgageDetailsRepository mortgageRepo,
                               PropertyDetailsRepository propertyRepo,
                               ModelMapper modelMapper) {
        this.mortgageRepo = mortgageRepo;
        this.propertyRepo = propertyRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public MortgageDetailsResponseDto createMortgage(Long homeLoanId) {

        log.info("Creating mortgage for HomeLoan ID: {}", homeLoanId);

        PropertyDetails property = propertyRepo.findByHomeLoan_HomeLoanId(homeLoanId)
                .orElseThrow(() -> {
                    log.error("Property not found for HomeLoan ID: {}", homeLoanId);
                    return new RuntimeException("Property not found for loan");
                });

        if (property.getMortgageDetails() != null) {
            log.warn("Mortgage already exists for Property ID: {}",
                    property.getPropertyId());
            throw new RuntimeException("Mortgage already exists");
        }

        MortgageDetails mortgage = new MortgageDetails();
        mortgage.setPropertyDetails(property);
        mortgage.setMortgageStartDate(LocalDate.now());
        mortgage.setMortgageReleased(false);
        mortgage.setCreatedAt(LocalDateTime.now());

        MortgageDetails saved = mortgageRepo.save(mortgage);

        log.info("Mortgage created successfully. Mortgage ID: {}",
                saved.getMortgageId());

        return mapToDto(saved);
    }


    @Override
    public MortgageDetailsResponseDto getMortgage(Long mortgageId) {

        log.info("Fetching mortgage details. Mortgage ID: {}", mortgageId);

        MortgageDetails mortgage = mortgageRepo.findById(mortgageId)
                .orElseThrow(() -> {
                    log.error("Mortgage not found for ID: {}", mortgageId);
                    return new RuntimeException("Mortgage not found");
                });

        return mapToDto(mortgage);
    }


    @Override
    public MortgageDetailsResponseDto releaseMortgage(Long mortgageId) {

        log.info("Releasing mortgage. Mortgage ID: {}", mortgageId);

        MortgageDetails mortgage = mortgageRepo.findById(mortgageId)
                .orElseThrow(() -> {
                    log.error("Mortgage not found for ID: {}", mortgageId);
                    return new RuntimeException("Mortgage not found");
                });

        if (Boolean.TRUE.equals(mortgage.getMortgageReleased())) {
            log.warn("Mortgage already released. Mortgage ID: {}", mortgageId);
            throw new RuntimeException("Mortgage already released");
        }

        mortgage.setMortgageReleased(true);
        mortgage.setMortgageReleaseDate(LocalDate.now());

        MortgageDetails updated = mortgageRepo.save(mortgage);

        log.info("Mortgage released successfully. Mortgage ID: {}", mortgageId);

        return mapToDto(updated);
    }

    @Override
    public void deleteMortgage(Long mortgageId) {

        log.info("Deleting mortgage. Mortgage ID: {}", mortgageId);

        MortgageDetails mortgage = mortgageRepo.findById(mortgageId)
                .orElseThrow(() -> {
                    log.error("Mortgage not found for ID: {}", mortgageId);
                    return new RuntimeException("Mortgage not found");
                });

        mortgageRepo.delete(mortgage);

        log.info("Mortgage deleted successfully. Mortgage ID: {}", mortgageId);
    }


    private MortgageDetailsResponseDto mapToDto(MortgageDetails mortgage) {

        MortgageDetailsResponseDto dto =
                modelMapper.map(mortgage, MortgageDetailsResponseDto.class);

        if (mortgage.getPropertyDetails() != null) {
            dto.setPropertyId(mortgage.getPropertyDetails().getPropertyId());
            log.debug("Mapped Property ID: {} to Mortgage DTO",
                    mortgage.getPropertyDetails().getPropertyId());
        }

        return dto;
    }
}
