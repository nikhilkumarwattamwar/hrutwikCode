package com.loanapp.loanManagementSystem.service.homeloan.service;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanDocument;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HomeLoanDocumentService {
    // ===============================
    // CREATE (UPLOAD)
    // ===============================
    HomeLoanDocument uploadDocument(
            Long homeLoanId,
            MultipartFile file,
            String documentType,
            Long uploadedBy) throws IOException;

    // ===============================
    // READ (METADATA)
    // ===============================
    HomeLoanDocument getDocumentById(Long documentId);

    List<HomeLoanDocument> getDocumentsByHomeLoan(Long homeLoanId);

    // ===============================
    // DOWNLOAD (S3 FILE)
    // ===============================
    byte[] downloadDocument(Long documentId);

    // ===============================
    // UPDATE (REPLACE FILE)
    // ===============================
    HomeLoanDocument updateDocument(
            Long documentId,
            MultipartFile newFile) throws IOException;

    // ===============================
    // DELETE (SOFT DELETE)
    // ===============================
    void deleteDocument(Long documentId);

    // ===============================
    // VERIFY
    // ===============================
    HomeLoanDocument verifyDocument(
            Long documentId,
            String status,
            Long verifiedBy);
}
