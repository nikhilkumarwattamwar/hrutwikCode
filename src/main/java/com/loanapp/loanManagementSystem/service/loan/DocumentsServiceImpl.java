package com.loanapp.loanManagementSystem.service.loan;

import com.loanapp.loanManagementSystem.dto.loan.DocumentsDto;
import com.loanapp.loanManagementSystem.entities.loan.Documents;
import com.loanapp.loanManagementSystem.entities.loan.Loan;
import com.loanapp.loanManagementSystem.enums.DocumentType;
import com.loanapp.loanManagementSystem.exception.ResourceNotFoundException;
import com.loanapp.loanManagementSystem.repository.DocumentsRepository;
import com.loanapp.loanManagementSystem.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentsServiceImpl implements DocumentsService{

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private DocumentsRepository documentsRepository;

    @Override
    public DocumentsDto uploadDocument(UUID loanId, DocumentType documentType, MultipartFile file) {
        Loan loan=loanRepository.findByLoanIdAndIsActiveTrue(loanId).orElseThrow(()->{
            return  new ResourceNotFoundException("Loan not found");
        });

        try {
            Documents documents= new Documents();
            documents.setLoan(loan);
            documents.setDocumentType(documentType);
            documents.setContentType(file.getContentType());
            documents.setFileName(file.getOriginalFilename());
            documents.setData(file.getBytes());

            Documents saved=documentsRepository.save(documents);
            return new DocumentsDto(saved.getDocumentId(), saved.getDocumentType(), saved.getFileName(), saved.getContentType());
        }catch (Exception e){
               throw  new RuntimeException("failed to store a document");
        }
    }

    @Override
    public byte[] downloadDocument(Integer documentId) {
        Documents documents=documentsRepository.findById(documentId).orElseThrow(()->new ResourceNotFoundException("documet not found"));

        return documents.getData();
    }

    @Override
    public String softDeleteDocument(Integer documentId) {
        Documents documents=documentsRepository.findById(documentId).orElseThrow(()->new ResourceNotFoundException("documet not found"));

        documents.setActive(false);

        documentsRepository.save(documents);

        return  "Documets deleted successfully ";


    }
}
