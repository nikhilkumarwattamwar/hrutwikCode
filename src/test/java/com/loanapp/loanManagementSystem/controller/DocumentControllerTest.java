package com.loanapp.loanManagementSystem.controller;

import com.loanapp.loanManagementSystem.contollers.loan.DocumentsController;
import com.loanapp.loanManagementSystem.dto.loan.DocumentsDto;
import com.loanapp.loanManagementSystem.enums.DocumentType;
import com.loanapp.loanManagementSystem.service.loan.DocumentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DocumentControllerTest {


    @InjectMocks
    private DocumentsController controller;

    @Mock
    private DocumentsService service;

    UUID loanId;
    DocumentsDto dto;

    @BeforeEach
    void setUp() {
        loanId = UUID.randomUUID();
        dto = new DocumentsDto(
                1,
                DocumentType.PAN,
                "pan.pdf",
                "app/pdf"
        );
    }

    @Test
    @DisplayName("uploadDocument : uploading document for existing loan")
    void testUploadDocument() {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "pan.pdf",
                "app/pdf",
                "dummy-data".getBytes()
        );

        when(service.uploadDocument(loanId, DocumentType.PAN, file))
                .thenReturn(dto);

        DocumentsDto result =
                controller.uploadDocumet(DocumentType.PAN, file, loanId);

        assertEquals(dto, result);
        verify(service, times(1))
                .uploadDocument(loanId, DocumentType.PAN, file);
    }

    @Test
    @DisplayName("downloadDocument : downloading document by documentId")
    void testDownloadDocument() {
        byte[] data = "file-content".getBytes();

        when(service.downloadDocument(1)).thenReturn(data);

        byte[] result = controller.downloadDocument(1);

        assertNotNull(result);
        assertArrayEquals(data, result);
        verify(service, times(1)).downloadDocument(1);
    }

    @Test
    @DisplayName("softDelete : soft deleting document by documentId")
    void testSoftDelete() {
        when(service.softDeleteDocument(1)).thenReturn("document deleted");

        controller.softDelete(1);

        verify(service, times(1)).softDeleteDocument(1);
    }

}
