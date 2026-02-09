package com.loanapp.loanManagementSystem.dto.homeloandto;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class PropertyDetailsRequestDto {

    private String propertyType;
    private String propertyAddress;
    private String city;
    private String state;
    private String pincode;

    private BigDecimal marketValue;
    private BigDecimal agreementValue;

    private String constructionStatus;
}

