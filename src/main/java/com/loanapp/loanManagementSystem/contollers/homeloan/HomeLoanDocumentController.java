//package com.loanapp.loanManagementSystem.contollers.homeloan;
//
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanDocument;
//import com.loanapp.loanManagementSystem.service.homeloan.service.HomeLoanDocumentService;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/s3/documents")
//public class HomeLoanDocumentController {
//
////    private final HomeLoanDocumentService documentService;
////
////    public HomeLoanDocumentController(HomeLoanDocumentService documentService) {
////        this.documentService = documentService;
////    }
////
////    @PostMapping("/verify/{documentId}")
////    public ResponseEntity<HomeLoanDocument> verifyDocument(
////            @PathVariable Long documentId,
////            @RequestParam String status,
////            @RequestParam(required = false) Long verifiedBy) {
////
////        HomeLoanDocument document =
////                documentService.verifyDocument(documentId, status, verifiedBy);
////
////        return ResponseEntity.ok(document);
////
////    }
////
////
////
////
////
////
////    // CREATE
////    @PostMapping("/upload")
////    public HomeLoanDocument upload(
////            @RequestParam Long homeLoanId,
////            @RequestParam MultipartFile file,
////            @RequestParam String documentType,
////            @RequestParam Long uploadedBy) throws IOException {
////
////        return documentService.uploadDocument(
////                homeLoanId, file, documentType, uploadedBy);
////    }
////
////    // READ ONE
////    @GetMapping("/{documentId}")
////    public HomeLoanDocument get(@PathVariable Long documentId) {
////        return documentService.getDocumentById(documentId);
////    }
////
////    // READ ALL FOR LOAN
////    @GetMapping("/home-loan/{homeLoanId}")
////    public List<HomeLoanDocument> getByLoan(@PathVariable Long homeLoanId) {
////        return documentService.getDocumentsByHomeLoan(homeLoanId);
////    }
////
////    // UPDATE
////    @PutMapping("/{documentId}")
////    public HomeLoanDocument update(
////            @PathVariable Long documentId,
////            @RequestParam MultipartFile file) throws IOException {
////
////        return documentService.updateDocument(documentId, file);
////    }
////
////    // DELETE
////    @DeleteMapping("/{documentId}")
////    public void delete(@PathVariable Long documentId) {
////        documentService.deleteDocument(documentId);
////    }
////
////    // VERIFY
//////    @PostMapping("/verify/{documentId}")
//////    public HomeLoanDocument verify(
//////            @PathVariable Long documentId,
//////            @RequestParam String status,
//////            @RequestParam Long verifiedBy) {
//////
//////        return documentService.verifyDocument(documentId, status, verifiedBy);
//////    }
//
//
//
//    private final HomeLoanDocumentService documentService;
//
//    public HomeLoanDocumentController(HomeLoanDocumentService documentService) {
//        this.documentService = documentService;
//    }
//
//    // =====================================================
//    // CREATE (UPLOAD)
//    // =====================================================
//    @PostMapping("/upload")
//    public ResponseEntity<HomeLoanDocument> upload(
//            @RequestParam Long homeLoanId,
//            @RequestParam MultipartFile file,
//            @RequestParam String documentType,
//            @RequestParam Long uploadedBy) throws IOException {
//
//        HomeLoanDocument document = documentService.uploadDocument(
//                homeLoanId, file, documentType, uploadedBy);
//
//        return ResponseEntity.ok(document);
//    }
//
//    // =====================================================
//    // READ (METADATA)
//    // =====================================================
//    @GetMapping("/{documentId}")
//    public ResponseEntity<HomeLoanDocument> get(
//            @PathVariable Long documentId) {
//
//        return ResponseEntity.ok(
//                documentService.getDocumentById(documentId));
//    }
//
//    // =====================================================
//    // READ ALL FOR LOAN
//    // =====================================================
//    @GetMapping("/home-loan/{homeLoanId}")
//    public ResponseEntity<List<HomeLoanDocument>> getByLoan(
//            @PathVariable Long homeLoanId) {
//
//        return ResponseEntity.ok(
//                documentService.getDocumentsByHomeLoan(homeLoanId));
//    }
//
//    // =====================================================
//    // DOWNLOAD (🔥 IMPORTANT)
//    // =====================================================
//    @GetMapping("/download/{documentId}")
//    public ResponseEntity<byte[]> download(@PathVariable Long documentId) {
//
//        HomeLoanDocument doc = documentService.getDocumentById(documentId);
//        byte[] fileData = documentService.downloadDocument(documentId);
//
//        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
//
//        if (doc.getContentType() != null && !doc.getContentType().isBlank()) {
//            mediaType = MediaType.parseMediaType(doc.getContentType());
//        }
//
//        return ResponseEntity.ok()
//                .contentType(mediaType)
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "inline; filename=\"" + doc.getOriginalFileName() + "\"")
//                .body(fileData);
//    }
//
//    // =====================================================
//    // UPDATE (REPLACE FILE)
//    // =====================================================
//    @PutMapping("/{documentId}")
//    public ResponseEntity<HomeLoanDocument> update(
//            @PathVariable Long documentId,
//            @RequestParam MultipartFile file) throws IOException {
//
//        return ResponseEntity.ok(
//                documentService.updateDocument(documentId, file));
//    }
//
//    // =====================================================
//    // DELETE
//    // =====================================================
//    @DeleteMapping("/{documentId}")
//    public ResponseEntity<Void> delete(
//            @PathVariable Long documentId) {
//
//        documentService.deleteDocument(documentId);
//        return ResponseEntity.noContent().build();
//    }
//
//    // =====================================================
//    // VERIFY
//    // =====================================================
//    @PostMapping("/verify/{documentId}")
//    public ResponseEntity<HomeLoanDocument> verifyDocument(
//            @PathVariable Long documentId,
//            @RequestParam String status,
//            @RequestParam(required = false) Long verifiedBy) {
//
//        HomeLoanDocument document =
//                documentService.verifyDocument(documentId, status, verifiedBy);
//
//        return ResponseEntity.ok(document);
//    }
//}
package com.loanapp.loanManagementSystem.contollers.homeloan;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanDocument;
import com.loanapp.loanManagementSystem.service.homeloan.service.HomeLoanDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/s3/documents")
@Slf4j
public class HomeLoanDocumentController {

    private final HomeLoanDocumentService documentService;

    public HomeLoanDocumentController(HomeLoanDocumentService documentService) {
        this.documentService = documentService;
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<HomeLoanDocument> upload(
            @RequestParam Long homeLoanId,
            @RequestParam MultipartFile file,
            @RequestParam String documentType,
            @RequestParam Long uploadedBy) throws IOException {

        log.info("Upload document request received. HomeLoan ID: {}, Type: {}",
                homeLoanId, documentType);

        HomeLoanDocument document =
                documentService.uploadDocument(homeLoanId, file, documentType, uploadedBy);

        log.info("Document uploaded successfully. Document ID: {}",
                document.getDocumentId());

        return ResponseEntity.ok(document);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{documentId}")
    public ResponseEntity<HomeLoanDocument> get(
            @PathVariable Long documentId) {

        log.info("Fetch document metadata request. Document ID: {}", documentId);

        HomeLoanDocument document = documentService.getDocumentById(documentId);

        return ResponseEntity.ok(document);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/home-loan/{homeLoanId}")
    public ResponseEntity<List<HomeLoanDocument>> getByLoan(
            @PathVariable Long homeLoanId) {

        log.info("Fetch documents for HomeLoan ID: {}", homeLoanId);

        List<HomeLoanDocument> documents =
                documentService.getDocumentsByHomeLoan(homeLoanId);

        log.info("Fetched {} documents for HomeLoan ID: {}",
                documents.size(), homeLoanId);

        return ResponseEntity.ok(documents);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/download/{documentId}")
    public ResponseEntity<byte[]> download(@PathVariable Long documentId) {

        log.info("Download document request. Document ID: {}", documentId);

        HomeLoanDocument doc = documentService.getDocumentById(documentId);
        byte[] fileData = documentService.downloadDocument(documentId);

        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
        if (doc.getContentType() != null && !doc.getContentType().isBlank()) {
            mediaType = MediaType.parseMediaType(doc.getContentType());
        }

        log.info("Document download successful. Document ID: {}", documentId);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + doc.getOriginalFileName() + "\"")
                .body(fileData);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/{documentId}")
    public ResponseEntity<HomeLoanDocument> update(
            @PathVariable Long documentId,
            @RequestParam MultipartFile file) throws IOException {

        log.info("Update document request. Document ID: {}", documentId);

        HomeLoanDocument updated =
                documentService.updateDocument(documentId, file);

        log.info("Document updated successfully. Document ID: {}", documentId);

        return ResponseEntity.ok(updated);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{documentId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long documentId) {

        log.info("Delete document request. Document ID: {}", documentId);

        documentService.deleteDocument(documentId);

        log.info("Document deleted successfully. Document ID: {}", documentId);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/verify/{documentId}")
    public ResponseEntity<HomeLoanDocument> verifyDocument(
            @PathVariable Long documentId,
            @RequestParam String status,
            @RequestParam(required = false) Long verifiedBy) {

        log.info("Verify document request. Document ID: {}, Status: {}",
                documentId, status);

        HomeLoanDocument document =
                documentService.verifyDocument(documentId, status, verifiedBy);

        log.info("Document verification completed. Document ID: {}, Final Status: {}",
                documentId, document.getVerificationStatus());

        return ResponseEntity.ok(document);
    }
}
