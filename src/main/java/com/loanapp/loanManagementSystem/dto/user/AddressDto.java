package com.loanapp.loanManagementSystem.dto.user;

import com.loanapp.loanManagementSystem.enums.AddressType;
import com.loanapp.loanManagementSystem.enums.HouseOwnership;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    AddressType type;

    private String landmark;

    private String city;


    private String district;

    private String state;

    private String country;

    @Pattern(regexp = "\\d{6}", message = "Pin code must be 6 digits")
    @Size(min = 6, max = 6)
    private String pinCode;

    private HouseOwnership houseOwnedBy;

    @Min(value = 0, message = "Years cannot be negative")
    private Integer yearsAtCurrentResidence;


}
