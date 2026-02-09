package com.loanapp.loanManagementSystem.service.homeloan.service;

import com.loanapp.loanManagementSystem.dto.homeloandto.PropertyDetailsRequestDto;
import com.loanapp.loanManagementSystem.dto.homeloandto.PropertyDetailsResponseDto;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.PropertyDetails;

public interface PropertyDetailsService {
    PropertyDetails addPropertyDetails(Long homeLoanId, PropertyDetailsRequestDto dto);





        PropertyDetailsResponseDto getPropertyById(Long propertyId);


        PropertyDetails getPropertyByHomeLoanId(Long homeLoanId);


        PropertyDetails updateProperty(Long propertyId, PropertyDetailsRequestDto dto);


        void deleteProperty(Long propertyId);


}

