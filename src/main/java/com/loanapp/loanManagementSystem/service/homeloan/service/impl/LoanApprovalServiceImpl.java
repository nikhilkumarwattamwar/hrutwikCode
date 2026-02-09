//package com.loanapp.loanManagementSystem.service.homeloan.service.impl;
//
//
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanApproval;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanApprovalRepository;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
//import com.loanapp.loanManagementSystem.service.homeloan.service.LoanApprovalService;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class LoanApprovalServiceImpl implements LoanApprovalService {
//
//    private final HomeLoanRepository homeLoanRepository;
//    private final HomeLoanApprovalRepository approvalRepository;
//
//    public LoanApprovalServiceImpl(HomeLoanRepository homeLoanRepository,
//                                   HomeLoanApprovalRepository approvalRepository) {
//        this.homeLoanRepository = homeLoanRepository;
//        this.approvalRepository = approvalRepository;
//    }
//
//    @Override
//    @Transactional   // 🔥 CORRECT PLACE
//    public String approveLoan(Long homeLoanId,
//                              Long approvedBy,
//                              String remarks) {
//
//        // 1️⃣ Fetch loan
//        HomeLoanData homeLoan = homeLoanRepository.findById(homeLoanId)
//                .orElseThrow(() -> new RuntimeException("Home loan not found"));
//
//        // 2️⃣ Prevent duplicate approval
//        if (approvalRepository.existsByHomeLoan(homeLoan)) {
//            throw new RuntimeException("Loan already approved");
//        }
//
//        // 3️⃣ Save approval record
//        HomeLoanApproval approval = new HomeLoanApproval();
//        approval.setHomeLoan(homeLoan);
//        approval.setApprovalStatus("APPROVED");
//        approval.setApprovedAmount(homeLoan.getRequestedAmount());
//        approval.setApprovedBy(approvedBy);
//        approval.setApprovedAt(LocalDateTime.now());
//        approval.setRemarks(remarks);
//
//        approvalRepository.save(approval);
//
//        // 4️⃣ Update master loan table
//        homeLoan.setLoanStatus("APPROVED");
//        homeLoan.setApprovedAmount(homeLoan.getRequestedAmount());
//        homeLoan.setUpdatedAt(LocalDateTime.now());
//
//        homeLoanRepository.save(homeLoan);
//
//        return "APPROVED";
//    }
//}
//
package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanApproval;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanApprovalRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.LoanApprovalService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class LoanApprovalServiceImpl implements LoanApprovalService {

    private final HomeLoanRepository homeLoanRepository;
    private final HomeLoanApprovalRepository approvalRepository;

    public LoanApprovalServiceImpl(HomeLoanRepository homeLoanRepository,
                                   HomeLoanApprovalRepository approvalRepository) {
        this.homeLoanRepository = homeLoanRepository;
        this.approvalRepository = approvalRepository;
    }

    @Override
    @Transactional
    public String approveLoan(Long homeLoanId,
                              Long approvedBy,
                              String remarks) {

        log.info("Starting loan approval. HomeLoan ID: {}, Approved By: {}",
                homeLoanId, approvedBy);


        HomeLoanData homeLoan = homeLoanRepository.findById(homeLoanId)
                .orElseThrow(() -> {
                    log.error("Home loan not found for ID: {}", homeLoanId);
                    return new RuntimeException("Home loan not found");
                });


        if (approvalRepository.existsByHomeLoan(homeLoan)) {
            log.warn("Duplicate approval attempt detected for HomeLoan ID: {}", homeLoanId);
            throw new RuntimeException("Loan already approved");
        }


        HomeLoanApproval approval = new HomeLoanApproval();
        approval.setHomeLoan(homeLoan);
        approval.setApprovalStatus("APPROVED");
        approval.setApprovedAmount(homeLoan.getRequestedAmount());
        approval.setApprovedBy(approvedBy);
        approval.setApprovedAt(LocalDateTime.now());
        approval.setRemarks(remarks);

        approvalRepository.save(approval);

        log.info("Loan approval record saved for HomeLoan ID: {}", homeLoanId);


        homeLoan.setLoanStatus("APPROVED");
        homeLoan.setApprovedAmount(homeLoan.getRequestedAmount());
        homeLoan.setUpdatedAt(LocalDateTime.now());

        homeLoanRepository.save(homeLoan);

        log.info("Home loan status updated to APPROVED. HomeLoan ID: {}", homeLoanId);

        return "APPROVED";
    }
}
