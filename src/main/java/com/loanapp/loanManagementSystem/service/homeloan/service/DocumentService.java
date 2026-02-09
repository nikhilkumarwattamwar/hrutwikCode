package com.loanapp.loanManagementSystem.service.homeloan.service;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanDocument;

public interface DocumentService {
    void upload(Long loanId, HomeLoanDocument document);
    void verify(Long documentId, String status);
}
