package com.loanapp.loanManagementSystem.contollers.s3controller;

import com.loanapp.loanManagementSystem.entities.loan.homeLoan.HomeLoanDocument;
import com.loanapp.loanManagementSystem.enums.DocumentType;
import com.loanapp.loanManagementSystem.service.homeloan.service.HomeLoanDocumentService;
import com.loanapp.loanManagementSystem.service.s3service.S3Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/s3")
public class S3Controller {


   final private S3Service s3Service;
    private final HomeLoanDocumentService documentService;


    public S3Controller(S3Service s3Service, HomeLoanDocumentService documentService) {
        this.s3Service = s3Service;
        this.documentService = documentService;
    }

//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadDocument(
//            @RequestParam Long homeLoanId,
//            @RequestParam MultipartFile file,
//            @RequestParam DocumentType documentType,
//            @RequestParam Long uploadedBy) throws IOException {
//
//        HomeLoanDocument document =
//                documentService.uploadDocument(homeLoanId, file, documentType, uploadedBy);
//
//        return ResponseEntity.ok(document);
//    }








//    @GetMapping("/download/{documentId}")
//    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long documentId) {
//
//        HomeLoanDocument document = documentService.getDocumentForDownload(documentId);
//
//        byte[] fileData = s3Service.downloadFileByPath(document.getDocumentPath());
//
//        MediaType mediaType = (document.getContentType() != null && !document.getContentType().isBlank())
//                ? MediaType.parseMediaType(document.getContentType())
//                : MediaType.APPLICATION_OCTET_STREAM;
//
//        String fileName = (document.getOriginalFileName() != null && !document.getOriginalFileName().isBlank())
//                ? document.getOriginalFileName()
//                : "document_" + document.getDocumentId();
//
//        return ResponseEntity.ok()
//                .contentType(mediaType)
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "attachment; filename=\"" + fileName + "\"")
//                .contentLength(fileData.length)
//                .body(fileData);
//    }










    @PutMapping("/update/{fileName}")
    public ResponseEntity<String> update(
            @PathVariable String fileName,
            @RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        s3Service.updateFile(fileName, file);

        return ResponseEntity
                .ok("File updated successfully: " + fileName);
    }


    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<Void> delete(@PathVariable String fileName) {

        s3Service.deleteFile(fileName);

        return ResponseEntity.noContent().build();
    }

}
