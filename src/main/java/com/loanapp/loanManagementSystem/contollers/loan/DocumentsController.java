package com.loanapp.loanManagementSystem.contollers.loan;

import com.loanapp.loanManagementSystem.dto.loan.DocumentsDto;
import com.loanapp.loanManagementSystem.enums.DocumentType;
import com.loanapp.loanManagementSystem.service.loan.DocumentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/loan/{loanId}/documets/")
public class DocumentsController {

    private static final Logger log = LoggerFactory.getLogger(DocumentsController.class);
    @Autowired
    DocumentsService service;

    @PostMapping("/upload")
    public DocumentsDto uploadDocumet(@RequestParam DocumentType documentType,
                                      @RequestParam MultipartFile file,
                                      @PathVariable UUID loanId) {

        log.info("Uploading document for loanId {}", loanId);
        return service.uploadDocument(loanId, documentType, file);
    }

    @GetMapping("/{documentId}")
    public byte[] downloadDocument(@PathVariable Integer documentId) {
        return service.downloadDocument(documentId);
    }

    @DeleteMapping("/{documentId}")
    public void softDelete(@PathVariable Integer documentId) {
        service.softDeleteDocument(documentId);
    }

}
