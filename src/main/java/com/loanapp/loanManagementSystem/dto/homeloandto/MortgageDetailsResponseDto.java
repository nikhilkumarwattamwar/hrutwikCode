package com.loanapp.loanManagementSystem.dto.homeloandto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class MortgageDetailsResponseDto {

    private Long mortgageId;
    private Long propertyId;
    private LocalDate mortgageStartDate;
    private Boolean mortgageReleased;
    private LocalDate mortgageReleaseDate;
    private LocalDateTime createdAt;


}

