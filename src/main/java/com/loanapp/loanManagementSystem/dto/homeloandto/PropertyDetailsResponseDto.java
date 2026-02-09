package com.loanapp.loanManagementSystem.dto.homeloandto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PropertyDetailsResponseDto {

    private Long propertyId;
    private String propertyType;
    private String propertyAddress;
    private BigDecimal propertyValue;
    private String ownershipType;

    // Minimal home-loan reference
    private Long homeLoanId;

    private LocalDateTime createdAt;

    // getters & setters
}

