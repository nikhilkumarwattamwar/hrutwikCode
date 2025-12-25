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

    @NotNull(message = "Address type is mandatory")
    AddressType type;

    private String landmark;

    @NotBlank(message = "City is mandatory")
    private String city;


    private String district;


    @NotBlank(message = "State is mandatory")
    private String state;


    @NotBlank(message = "Country is mandatory")
    private String country;


    @NotBlank(message = "Pin code is mandatory")
    @Pattern(regexp = "\\d{6}", message = "Pin code must be 6 digits")
    @Size(min = 6, max = 6)
    private String pinCode;

    @NotNull(message = "House ownership status is required")
    private HouseOwnership houseOwnedBy;

    @NotNull(message = "Years at current residence is mandatory")
    @Min(value = 0, message = "Years cannot be negative")
    private Integer yearsAtCurrentResidence;


}
