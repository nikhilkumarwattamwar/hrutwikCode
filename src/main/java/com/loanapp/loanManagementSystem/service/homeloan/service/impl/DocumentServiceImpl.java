package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanDocument;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanDocumentRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.DocumentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private HomeLoanDocumentRepository docRepo;
    @Autowired
    private HomeLoanRepository loanRepo;

    @Override
    public void upload(Long loanId, HomeLoanDocument doc) {

        HomeLoanData loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        doc.setHomeLoan(loan);
        doc.setVerificationStatus("PENDING");
        doc.setIsActive(true);
        doc.setUploadedAt(LocalDateTime.now());

        docRepo.save(doc);
    }

    @Override
    public void verify(Long docId, String status) {

        HomeLoanDocument doc = docRepo.findById(docId)
                .orElseThrow(() -> new RuntimeException("Doc not found"));

        doc.setVerificationStatus(status);
        docRepo.save(doc);
    }
}

