//package com.loanapp.loanManagementSystem.service.homeloan.service.impl;
//
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanDocument;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanDocumentRepository;
//import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
//import com.loanapp.loanManagementSystem.service.homeloan.service.HomeLoanDocumentService;
//import com.loanapp.loanManagementSystem.service.s3service.S3Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class HomeLoanDocumentServiceImpl implements HomeLoanDocumentService {
//
//    private final HomeLoanDocumentRepository documentRepository;
//    private final HomeLoanRepository homeLoanRepository;
//    private final S3Service s3Service;
//
//    @Autowired
//    public HomeLoanDocumentServiceImpl(
//            HomeLoanDocumentRepository documentRepository,
//            HomeLoanRepository homeLoanRepository,
//            S3Service s3Service) {
//
//        this.documentRepository = documentRepository;
//        this.homeLoanRepository = homeLoanRepository;
//        this.s3Service = s3Service;
//    }
//
//    // =====================================================
//    // CREATE (UPLOAD)
//    // =====================================================
//    @Override
//    public HomeLoanDocument uploadDocument(
//            Long homeLoanId,
//            MultipartFile file,
//            String documentType,
//            Long uploadedBy) throws IOException {
//
//        HomeLoanData loan = homeLoanRepository.findById(homeLoanId)
//                .orElseThrow(() -> new RuntimeException("Home loan not found"));
//
//        // 1️⃣ Upload to S3
//        String s3Path = s3Service.uploadFile(file, homeLoanId, documentType);
//
//        // 2️⃣ Save metadata in DB
//        HomeLoanDocument document = new HomeLoanDocument();
//        String contentType = file.getContentType();
//
//        if (contentType == null || contentType.isBlank()) {
//            contentType = detectContentTypeFromFileName(file.getOriginalFilename());
//        }
//
//        document.setContentType(contentType);
//        document.setOriginalFileName(file.getOriginalFilename());
//
//        document.setHomeLoan(loan);
//        document.setDocumentType(documentType);
//        document.setDocumentPath(s3Path);
//        document.setUploadedBy(uploadedBy);
//        document.setVerificationStatus("PENDING");
//        document.setIsActive(true);
//        document.setUploadedAt(LocalDateTime.now());
//        document.setOriginalFileName(file.getOriginalFilename());
//        document.setContentType(file.getContentType());
//
//        return documentRepository.save(document);
//    }
//
//    // =====================================================
//    // READ (METADATA)
//    // =====================================================
//    @Override
//    public HomeLoanDocument getDocumentById(Long documentId) {
//
//        return documentRepository.findById(documentId)
//                .orElseThrow(() -> new RuntimeException("Document not found"));
//    }
//
//    // =====================================================
//    // READ (ALL FOR HOME LOAN)
//    // =====================================================
//    @Override
//    public List<HomeLoanDocument> getDocumentsByHomeLoan(Long homeLoanId) {
//
//        return documentRepository.findByHomeLoan_HomeLoanId(homeLoanId);
//    }
//
//    // =====================================================
//    // DOWNLOAD (CALLS S3)
//    // =====================================================
//    @Override
//    public byte[] downloadDocument(Long documentId) {
//
//        HomeLoanDocument document = getDocumentById(documentId);
//
//        if (!Boolean.TRUE.equals(document.getIsActive())) {
//            throw new RuntimeException("Document is inactive");
//        }
//
//        // 🔥 THIS IS THE IMPORTANT PART
//        return s3Service.downloadFileByPath(document.getDocumentPath());
//    }
//
//    // =====================================================
//    // UPDATE (REPLACE FILE)
//    // =====================================================
//    @Override
//    public HomeLoanDocument updateDocument(
//            Long documentId,
//            MultipartFile newFile) throws IOException {
//
//        HomeLoanDocument document = getDocumentById(documentId);
//
//        if (!Boolean.TRUE.equals(document.getIsActive())) {
//            throw new RuntimeException("Cannot update inactive document");
//        }
//
//        // Replace file in S3
//        s3Service.updateFile(document.getDocumentPath(), newFile);
//
//        document.setVerificationStatus("PENDING");
//        document.setUpdatedAt(LocalDateTime.now());
//
//        return documentRepository.save(document);
//    }
//
//    // =====================================================
//    // DELETE (SOFT DELETE + S3 DELETE)
//    // =====================================================
//    @Override
//    public void deleteDocument(Long documentId) {
//
//        HomeLoanDocument document = getDocumentById(documentId);
//
//        // Delete from S3
//        s3Service.deleteFile(document.getDocumentPath());
//
//        // Soft delete in DB
//        document.setIsActive(false);
//        document.setUpdatedAt(LocalDateTime.now());
//
//        documentRepository.save(document);
//    }
//
//    // =====================================================
//    // VERIFY
//    // =====================================================
//    @Override
//    public HomeLoanDocument verifyDocument(
//            Long documentId,
//            String status,
//            Long verifiedBy) {
//
//        HomeLoanDocument document = getDocumentById(documentId);
//
//        if (!Boolean.TRUE.equals(document.getIsActive())) {
//            throw new RuntimeException("Cannot verify inactive document");
//        }
//
//        if (!status.equalsIgnoreCase("VERIFIED")
//                && !status.equalsIgnoreCase("REJECTED")) {
//            throw new IllegalArgumentException("Invalid verification status");
//        }
//
//        document.setVerificationStatus(status.toUpperCase());
//        document.setVerifiedBy(verifiedBy);
//        document.setVerifiedAt(LocalDateTime.now());
//
//        return documentRepository.save(document);
//    }
//
//    private String detectContentTypeFromFileName(String fileName) {
//
//        if (fileName == null) return "application/octet-stream";
//
//        fileName = fileName.toLowerCase();
//
//        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))
//            return "image/jpeg";
//        if (fileName.endsWith(".png"))
//            return "image/png";
//        if (fileName.endsWith(".pdf"))
//            return "application/pdf";
//
//        return "application/octet-stream";
//    }
//
//}
package com.loanapp.loanManagementSystem.service.homeloan.service.impl;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanData;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanDocument;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanDocumentRepository;
import com.loanapp.loanManagementSystem.repository.homeloanrepo.HomeLoanRepository;
import com.loanapp.loanManagementSystem.service.homeloan.service.HomeLoanDocumentService;
import com.loanapp.loanManagementSystem.service.s3service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class HomeLoanDocumentServiceImpl implements HomeLoanDocumentService {

    private final HomeLoanDocumentRepository documentRepository;
    private final HomeLoanRepository homeLoanRepository;
    private final S3Service s3Service;

    @Autowired
    public HomeLoanDocumentServiceImpl(
            HomeLoanDocumentRepository documentRepository,
            HomeLoanRepository homeLoanRepository,
            S3Service s3Service) {

        this.documentRepository = documentRepository;
        this.homeLoanRepository = homeLoanRepository;
        this.s3Service = s3Service;
    }

    // =====================================================
    // UPLOAD DOCUMENT
    // =====================================================
    @Override
    public HomeLoanDocument uploadDocument(
            Long homeLoanId,
            MultipartFile file,
            String documentType,
            Long uploadedBy) throws IOException {

        log.info("Uploading document for HomeLoan ID: {}, Type: {}", homeLoanId, documentType);

        HomeLoanData loan = homeLoanRepository.findById(homeLoanId)
                .orElseThrow(() -> {
                    log.error("Home loan not found for ID: {}", homeLoanId);
                    return new RuntimeException("Home loan not found");
                });

        String s3Path = s3Service.uploadFile(file, homeLoanId, documentType);
        log.debug("File uploaded to S3 at path: {}", s3Path);

        HomeLoanDocument document = new HomeLoanDocument();

        String contentType = file.getContentType();
        if (contentType == null || contentType.isBlank()) {
            contentType = detectContentTypeFromFileName(file.getOriginalFilename());
            log.debug("Detected content type from filename: {}", contentType);
        }

        document.setHomeLoan(loan);
        document.setDocumentType(documentType);
        document.setDocumentPath(s3Path);
        document.setUploadedBy(uploadedBy);
        document.setVerificationStatus("PENDING");
        document.setIsActive(true);
        document.setUploadedAt(LocalDateTime.now());
        document.setOriginalFileName(file.getOriginalFilename());
        document.setContentType(contentType);

        HomeLoanDocument saved = documentRepository.save(document);

        log.info("Document uploaded successfully. Document ID: {}", saved.getDocumentId());

        return saved;
    }

    // =====================================================
    // GET DOCUMENT BY ID
    // =====================================================
    @Override
    public HomeLoanDocument getDocumentById(Long documentId) {

        log.debug("Fetching document metadata for Document ID: {}", documentId);

        return documentRepository.findById(documentId)
                .orElseThrow(() -> {
                    log.error("Document not found for ID: {}", documentId);
                    return new RuntimeException("Document not found");
                });
    }

    // =====================================================
    // GET ALL DOCUMENTS FOR HOME LOAN
    // =====================================================
    @Override
    public List<HomeLoanDocument> getDocumentsByHomeLoan(Long homeLoanId) {

        log.info("Fetching all documents for HomeLoan ID: {}", homeLoanId);

        List<HomeLoanDocument> documents =
                documentRepository.findByHomeLoan_HomeLoanId(homeLoanId);

        log.debug("Total documents found: {}", documents.size());

        return documents;
    }

    // =====================================================
    // DOWNLOAD DOCUMENT
    // =====================================================
    @Override
    public byte[] downloadDocument(Long documentId) {

        log.info("Downloading document. Document ID: {}", documentId);

        HomeLoanDocument document = getDocumentById(documentId);

        if (!Boolean.TRUE.equals(document.getIsActive())) {
            log.warn("Attempt to download inactive document. Document ID: {}", documentId);
            throw new RuntimeException("Document is inactive");
        }

        byte[] data = s3Service.downloadFileByPath(document.getDocumentPath());

        log.info("Document downloaded successfully. Document ID: {}", documentId);

        return data;
    }

    // =====================================================
    // UPDATE DOCUMENT
    // =====================================================
    @Override
    public HomeLoanDocument updateDocument(
            Long documentId,
            MultipartFile newFile) throws IOException {

        log.info("Updating document file. Document ID: {}", documentId);

        HomeLoanDocument document = getDocumentById(documentId);

        if (!Boolean.TRUE.equals(document.getIsActive())) {
            log.warn("Cannot update inactive document. Document ID: {}", documentId);
            throw new RuntimeException("Cannot update inactive document");
        }

        s3Service.updateFile(document.getDocumentPath(), newFile);
        log.debug("File replaced in S3 for Document ID: {}", documentId);

        document.setVerificationStatus("PENDING");
        document.setUpdatedAt(LocalDateTime.now());

        HomeLoanDocument updated = documentRepository.save(document);

        log.info("Document updated successfully. Document ID: {}", documentId);

        return updated;
    }

    // =====================================================
    // DELETE DOCUMENT
    // =====================================================
    @Override
    public void deleteDocument(Long documentId) {

        log.info("Deleting document (soft delete). Document ID: {}", documentId);

        HomeLoanDocument document = getDocumentById(documentId);

        s3Service.deleteFile(document.getDocumentPath());
        log.debug("File deleted from S3 for Document ID: {}", documentId);

        document.setIsActive(false);
        document.setUpdatedAt(LocalDateTime.now());

        documentRepository.save(document);

        log.info("Document marked inactive successfully. Document ID: {}", documentId);
    }

    // =====================================================
    // VERIFY DOCUMENT
    // =====================================================
    @Override
    public HomeLoanDocument verifyDocument(
            Long documentId,
            String status,
            Long verifiedBy) {

        log.info("Verifying document. Document ID: {}, Status: {}", documentId, status);

        HomeLoanDocument document = getDocumentById(documentId);

        if (!Boolean.TRUE.equals(document.getIsActive())) {
            log.warn("Attempt to verify inactive document. Document ID: {}", documentId);
            throw new RuntimeException("Cannot verify inactive document");
        }

        if (!status.equalsIgnoreCase("VERIFIED")
                && !status.equalsIgnoreCase("REJECTED")) {
            log.error("Invalid verification status: {}", status);
            throw new IllegalArgumentException("Invalid verification status");
        }

        document.setVerificationStatus(status.toUpperCase());
        document.setVerifiedBy(verifiedBy);
        document.setVerifiedAt(LocalDateTime.now());

        HomeLoanDocument verified = documentRepository.save(document);

        log.info("Document verification completed. Document ID: {}, Final Status: {}",
                documentId, verified.getVerificationStatus());

        return verified;
    }

    // =====================================================
    // CONTENT TYPE DETECTION
    // =====================================================
    private String detectContentTypeFromFileName(String fileName) {

        if (fileName == null) return "application/octet-stream";

        fileName = fileName.toLowerCase();

        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))
            return "image/jpeg";
        if (fileName.endsWith(".png"))
            return "image/png";
        if (fileName.endsWith(".pdf"))
            return "application/pdf";

        return "application/octet-stream";
    }
}
