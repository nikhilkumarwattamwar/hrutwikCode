package com.loanapp.loanManagementSystem.dto;

import com.loanapp.loanManagementSystem.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDto {

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Marital status is required")
    private MaritalStatus maritalStatus;


    @NotBlank(message = "PAN Card Number is required")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN format")
    private String panCardNumber;

    @NotBlank(message = "Aadhaar Number is required")
    @Pattern(regexp = "\\d{12}", message = "Aadhaar must be 12 digits")
    private String aadharNumber;

    @Size(max = 20)
    @Pattern(regexp = "[A-Z]{2}\\d{7}", message = "Invalid passport format")
    private String passportNumber;

    private String voterIdNumber;

    @NotNull(message = "Disability status is required")
    @Enumerated(EnumType.STRING)
    private Disability disability;

    private Constitution constitution;

    @NotNull
    private Religion religion;

    @NotNull
    private Education education;

    @NotNull
    private Category category;

}
