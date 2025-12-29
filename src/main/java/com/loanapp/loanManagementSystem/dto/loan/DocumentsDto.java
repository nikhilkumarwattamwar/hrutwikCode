package com.loanapp.loanManagementSystem.dto.loan;

import com.loanapp.loanManagementSystem.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentsDto {
    private Integer documentId;
    private DocumentType documentType;
    private String fileName;
    private String contentType;
}
