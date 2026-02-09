package com.loanapp.loanManagementSystem.service.homeloan.service;


public interface LoanApprovalService {

    String approveLoan(Long homeLoanId, Long approvedBy, String remarks);
}
