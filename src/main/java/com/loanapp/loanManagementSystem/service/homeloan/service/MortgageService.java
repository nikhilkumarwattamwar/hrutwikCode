package com.loanapp.loanManagementSystem.service.homeloan.service;

import com.loanapp.loanManagementSystem.dto.homeloandto.MortgageDetailsResponseDto;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.MortgageDetails;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.PropertyDetails;

public interface MortgageService {
    MortgageDetailsResponseDto createMortgage(Long homeLoanId);

    MortgageDetailsResponseDto getMortgage(Long mortgageId);

    MortgageDetailsResponseDto releaseMortgage(Long mortgageId);

    void deleteMortgage(Long mortgageId);
}