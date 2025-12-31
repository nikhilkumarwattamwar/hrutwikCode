package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.loan.DocumentsDto;
import com.loanapp.loanManagementSystem.entities.loan.Documents;
import com.loanapp.loanManagementSystem.entities.loan.Loan;
import com.loanapp.loanManagementSystem.enums.DocumentType;
import com.loanapp.loanManagementSystem.exception.ResourceNotFoundException;
import com.loanapp.loanManagementSystem.repository.DocumentsRepository;
import com.loanapp.loanManagementSystem.repository.LoanRepository;
import com.loanapp.loanManagementSystem.service.loan.DocumentsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private DocumentsRepository documentsRepository;

    @InjectMocks
    private DocumentsServiceImpl documentsService;

    @Test
    @DisplayName("Upload document successfully")
    void TestUploadDocument() throws Exception {
        UUID loanId = UUID.randomUUID();

        Loan loan = new Loan();
        loan.setActive(true);

        MockMultipartFile file = new MockMultipartFile("file",
                "aadhaar.pdf",
                "application/pdf",
                "dummy data".getBytes()
        );

        Documents savedDocument = new Documents();
        savedDocument.setDocumentId(1);
        savedDocument.setDocumentType(DocumentType.AADHAAR);
        savedDocument.setFileName("aadhaar.pdf");
        savedDocument.setContentType("application/pdf");

        when(loanRepository.findByLoanIdAndIsActiveTrue(loanId))
                .thenReturn(Optional.of(loan));
        when(documentsRepository.save(any(Documents.class)))
                .thenReturn(savedDocument);

        DocumentsDto result =
                documentsService.uploadDocument(loanId, DocumentType.AADHAAR, file);

        assertNotNull(result);
        assertEquals(DocumentType.AADHAAR, result.getDocumentType());
        assertEquals("aadhaar.pdf", result.getFileName());

        verify(loanRepository, times(1))
                .findByLoanIdAndIsActiveTrue(loanId);
        verify(documentsRepository, times(1))
                .save(any(Documents.class));
    }

    @Test
    @DisplayName("Throw exception if loanId is not found")
    void testLoanIdNotFound() {
        UUID loanId = UUID.randomUUID();
        MultipartFile file = mock(MultipartFile.class);

        when(loanRepository.findByLoanIdAndIsActiveTrue(loanId)).thenReturn(Optional.empty());


        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> documentsService.uploadDocument(loanId, DocumentType.AADHAAR, file));

        assertEquals("Loan not found", ex.getMessage());

        verify(loanRepository).findByLoanIdAndIsActiveTrue(loanId);
    }

    @Test
    @DisplayName("Throw exception when failed to store a file")
    void testFailedToStoreFile() throws IOException {
        UUID id = UUID.randomUUID();
        Loan loan = mock(Loan.class);
        MultipartFile file = mock(MultipartFile.class);

        when(loanRepository.findByLoanIdAndIsActiveTrue(id)).thenReturn(Optional.ofNullable(loan));

        when(file.getBytes()).thenThrow(new IOException("File error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> documentsService.uploadDocument(id, DocumentType.PAN, file));
        assertEquals("failed to store a document", exception.getMessage());
    }

    @Test
    @DisplayName("Download document successfully")
    void downloadDocument_success() {
        Documents documents = new Documents();
        documents.setData("file-data".getBytes());

        when(documentsRepository.findById(1))
                .thenReturn(Optional.of(documents));

        byte[] result = documentsService.downloadDocument(1);

        assertNotNull(result);
        assertEquals("file-data", new String(result));

        verify(documentsRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Download document - document not found")
    void downloadDocumentNotFound() {
        when(documentsRepository.findById(99))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                documentsService.downloadDocument(99));
    }

    @Test
    @DisplayName("Soft delete document successfully")
    void testSoftDeleteDocument() {
        Documents documents = new Documents();
        documents.setActive(true);

        when(documentsRepository.findById(1))
                .thenReturn(Optional.of(documents));
        when(documentsRepository.save(any(Documents.class)))
                .thenReturn(documents);

        String result = documentsService.softDeleteDocument(1);

        assertEquals("Documents deleted successfully ", result);
        assertFalse(documents.isActive());

        verify(documentsRepository, times(1)).save(documents);
    }

}
