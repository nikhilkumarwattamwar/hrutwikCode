package com.loanapp.loanManagementSystem.service.homeloan.service;

import com.loanapp.loanManagementSystem.dto.homeloandto.HomeLoanRequestDto;

public interface HomeLoanService {
    Long apply(HomeLoanRequestDto dto);
    void approve(Long loanId);
    void close(Long loanId);
}