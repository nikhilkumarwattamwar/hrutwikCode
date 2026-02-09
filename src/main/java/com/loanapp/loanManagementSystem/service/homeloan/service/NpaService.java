package com.loanapp.loanManagementSystem.service.homeloan.service;


import com.loanapp.loanManagementSystem.dto.homeloandto.HomeLoanNpaResponseDto;

import java.util.List;

public interface NpaService {

    /**
     * 🔁 Auto NPA check
     * Used by scheduler
     * Marks loan as NPA if EMI overdue > 90 days
     */
    void checkAndMarkNpa();

    /**
     * 📄 Get all NPA loans
     */
    List<HomeLoanNpaResponseDto> getAllNpas();

    /**
     * 🔍 Get single NPA by ID
     */
    HomeLoanNpaResponseDto getNpa(Long npaId);

    /**
     * ✏️ Update NPA status manually (SUB_STANDARD / DOUBTFUL / LOSS)
     */
    HomeLoanNpaResponseDto updateStatus(Long npaId, String status);

    /**
     * ♻️ Cure NPA (loan regularized)
     * Sets loan status back to ACTIVE
     */
    HomeLoanNpaResponseDto cureNpa(Long npaId);
}
