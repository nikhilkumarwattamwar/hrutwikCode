//package com.loanapp.loanManagementSystem.service.homeloan.service.impl;
//
//import com.loanapp.loanManagementSystem.dto.homeloandto.PropertyDetailsRequestDto;
//import com.loanapp.loanManagementSystem.dto.homeloandto.PropertyDetailsResponseDto;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.PropertyDetails;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.PropertyDetailsRepository;
//import com.loanapp.loanManagementSystem.service.homeloan.service.PropertyDetailsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class PropertyDetailsServiceImpl implements PropertyDetailsService {
//
//    private final HomeLoanRepository homeLoanRepository;
//    private final PropertyDetailsRepository propertyDetailsRepository;
//
//    @Override
//    public PropertyDetails addPropertyDetails(Long homeLoanId, PropertyDetailsRequestDto dto) {
//
//        HomeLoanData homeLoan = homeLoanRepository.findById(homeLoanId)
//                .orElseThrow(() -> new RuntimeException("Home Loan not found"));
//
//        PropertyDetails property = new PropertyDetails();
//        property.setPropertyType(dto.getPropertyType());
//        property.setPropertyAddress(dto.getPropertyAddress());
//        property.setCity(dto.getCity());
//        property.setState(dto.getState());
//        property.setPincode(dto.getPincode());
//        property.setMarketValue(dto.getMarketValue());
//        property.setAgreementValue(dto.getAgreementValue());
//        property.setConstructionStatus(dto.getConstructionStatus());
//        property.setCreatedAt(LocalDateTime.now());
//
//        property.setHomeLoan(homeLoan);
//
//        return propertyDetailsRepository.save(property);
//    }
//
//    @Override
//    public PropertyDetails updateProperty(
//            Long propertyId,
//            PropertyDetailsRequestDto dto) {
//
//        PropertyDetails property = propertyDetailsRepository.findById(propertyId)
//                .orElseThrow(() -> new RuntimeException("Property not found"));
//
//        property.setPropertyType(dto.getPropertyType());
//        property.setPropertyAddress(dto.getPropertyAddress());
//        property.setCity(dto.getCity());
//        property.setState(dto.getState());
//        property.setPincode(dto.getPincode());
//        property.setMarketValue(dto.getMarketValue());
//        property.setAgreementValue(dto.getAgreementValue());
//        property.setConstructionStatus(dto.getConstructionStatus());
//
//        return propertyDetailsRepository.save(property);
//    }
//
//
//    @Override
//    public PropertyDetails getPropertyByHomeLoanId(Long homeLoanId) {
//
//        HomeLoanData loan = homeLoanRepository.findById(homeLoanId)
//                .orElseThrow(() -> new RuntimeException("Home Loan not found"));
//
//        if (loan.getPropertyDetails() == null) {
//            throw new RuntimeException("Property not added yet");
//        }
//
//        return loan.getPropertyDetails();
//    }
//
//    private PropertyDetailsResponseDto mapToResponseDto(PropertyDetails property) {
//
//        PropertyDetailsResponseDto dto = new PropertyDetailsResponseDto();
//
//        dto.setPropertyId(property.getPropertyId());
//        dto.setPropertyType(property.getPropertyType());
//        dto.setPropertyAddress(property.getPropertyAddress());
//        dto.setCreatedAt(property.getCreatedAt());
//
//        if (property.getHomeLoan() != null) {
//            dto.setHomeLoanId(property.getHomeLoan().getHomeLoanId());
//        }
//
//        return dto;
//    }
//
//    @Override
//    public PropertyDetailsResponseDto getPropertyById(Long propertyId) {
//        PropertyDetails property =  propertyDetailsRepository.findById(propertyId)
//                .orElseThrow(() -> new RuntimeException("Property not found"));
//
//        return mapToResponseDto(property);
//
//    }
//
//    @Override
//    public void deleteProperty(Long propertyId) {
//
//        PropertyDetails property = propertyDetailsRepository.findById(propertyId)
//                .orElseThrow(() -> new RuntimeException("Property not found"));
//
//        if (property.getMortgageDetails() != null) {
//            throw new RuntimeException("Cannot delete property with active mortgage");
//        }
//
//        propertyDetailsRepository.delete(property);
//    }
//
//}
//
package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import com.loanapp.loanManagementSystem.dto.homeloandto.PropertyDetailsRequestDto;
import com.loanapp.loanManagementSystem.dto.homeloandto.PropertyDetailsResponseDto;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.PropertyDetails;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.PropertyDetailsRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.PropertyDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyDetailsServiceImpl implements PropertyDetailsService {

    private final HomeLoanRepository homeLoanRepository;
    private final PropertyDetailsRepository propertyDetailsRepository;


    @Override
    public PropertyDetails addPropertyDetails(Long homeLoanId, PropertyDetailsRequestDto dto) {

        log.info("Adding property details for HomeLoan ID: {}", homeLoanId);

        HomeLoanData homeLoan = homeLoanRepository.findById(homeLoanId)
                .orElseThrow(() -> {
                    log.error("Home loan not found for ID: {}", homeLoanId);
                    return new RuntimeException("Home Loan not found");
                });

        PropertyDetails property = new PropertyDetails();
        property.setPropertyType(dto.getPropertyType());
        property.setPropertyAddress(dto.getPropertyAddress());
        property.setCity(dto.getCity());
        property.setState(dto.getState());
        property.setPincode(dto.getPincode());
        property.setMarketValue(dto.getMarketValue());
        property.setAgreementValue(dto.getAgreementValue());
        property.setConstructionStatus(dto.getConstructionStatus());
        property.setCreatedAt(LocalDateTime.now());
        property.setHomeLoan(homeLoan);

        PropertyDetails saved = propertyDetailsRepository.save(property);

        log.info("Property added successfully. Property ID: {}", saved.getPropertyId());

        return saved;
    }


    @Override
    public PropertyDetails updateProperty(Long propertyId,
                                          PropertyDetailsRequestDto dto) {

        log.info("Updating property details. Property ID: {}", propertyId);

        PropertyDetails property = propertyDetailsRepository.findById(propertyId)
                .orElseThrow(() -> {
                    log.error("Property not found for ID: {}", propertyId);
                    return new RuntimeException("Property not found");
                });

        property.setPropertyType(dto.getPropertyType());
        property.setPropertyAddress(dto.getPropertyAddress());
        property.setCity(dto.getCity());
        property.setState(dto.getState());
        property.setPincode(dto.getPincode());
        property.setMarketValue(dto.getMarketValue());
        property.setAgreementValue(dto.getAgreementValue());
        property.setConstructionStatus(dto.getConstructionStatus());

        PropertyDetails updated = propertyDetailsRepository.save(property);

        log.info("Property updated successfully. Property ID: {}", propertyId);

        return updated;
    }


    @Override
    public PropertyDetails getPropertyByHomeLoanId(Long homeLoanId) {

        log.info("Fetching property details for HomeLoan ID: {}", homeLoanId);

        HomeLoanData loan = homeLoanRepository.findById(homeLoanId)
                .orElseThrow(() -> {
                    log.error("Home loan not found for ID: {}", homeLoanId);
                    return new RuntimeException("Home Loan not found");
                });

        if (loan.getPropertyDetails() == null) {
            log.warn("Property not added yet for HomeLoan ID: {}", homeLoanId);
            throw new RuntimeException("Property not added yet");
        }

        return loan.getPropertyDetails();
    }


    @Override
    public PropertyDetailsResponseDto getPropertyById(Long propertyId) {

        log.info("Fetching property by Property ID: {}", propertyId);

        PropertyDetails property = propertyDetailsRepository.findById(propertyId)
                .orElseThrow(() -> {
                    log.error("Property not found for ID: {}", propertyId);
                    return new RuntimeException("Property not found");
                });

        return mapToResponseDto(property);
    }

    @Override
    public void deleteProperty(Long propertyId) {

        log.info("Deleting property. Property ID: {}", propertyId);

        PropertyDetails property = propertyDetailsRepository.findById(propertyId)
                .orElseThrow(() -> {
                    log.error("Property not found for ID: {}", propertyId);
                    return new RuntimeException("Property not found");
                });

        if (property.getMortgageDetails() != null) {
            log.warn("Cannot delete property with active mortgage. Property ID: {}", propertyId);
            throw new RuntimeException("Cannot delete property with active mortgage");
        }

        propertyDetailsRepository.delete(property);

        log.info("Property deleted successfully. Property ID: {}", propertyId);
    }


    private PropertyDetailsResponseDto mapToResponseDto(PropertyDetails property) {

        PropertyDetailsResponseDto dto = new PropertyDetailsResponseDto();
        dto.setPropertyId(property.getPropertyId());
        dto.setPropertyType(property.getPropertyType());
        dto.setPropertyAddress(property.getPropertyAddress());
        dto.setCreatedAt(property.getCreatedAt());

        if (property.getHomeLoan() != null) {
            dto.setHomeLoanId(property.getHomeLoan().getHomeLoanId());
            log.debug("Mapped HomeLoan ID {} to Property DTO",
                    property.getHomeLoan().getHomeLoanId());
        }

        return dto;
    }
}
