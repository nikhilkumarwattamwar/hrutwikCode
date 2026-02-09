package com.loanapp.loanManagementSystem.dto.homeloandto;

import lombok.Data;

@Data
public class HomeLoanDocumentDto {

    private Long documentId;
    private String documentType;
    private String originalFileName;
    private Boolean isActive;
}

