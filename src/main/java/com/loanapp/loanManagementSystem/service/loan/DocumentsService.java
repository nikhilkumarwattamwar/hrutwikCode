package com.loanapp.loanManagementSystem.service.loan;

import com.loanapp.loanManagementSystem.dto.loan.DocumentsDto;
import com.loanapp.loanManagementSystem.enums.DocumentType;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface DocumentsService {

    DocumentsDto uploadDocument(UUID loanId, DocumentType documentType, MultipartFile file);

    byte[] downloadDocument(Integer documentId);

    String softDeleteDocument(Integer documentId);

}
