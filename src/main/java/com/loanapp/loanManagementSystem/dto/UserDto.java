package com.loanapp.loanManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.loanapp.loanManagementSystem.enums.CustomerFetchType;
import com.loanapp.loanManagementSystem.enums.CustomerType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    UUID id;

    @NotBlank(message = "Name cannot be empty." )
    private String name;


    @Email(message = "Invalid email address. Please enter valid email")
    @NotBlank(message = "Please enter your email ID.")
    private String email;


    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;


    @NotNull(message = "Customer Type is required")
    private CustomerType customerType;


    private CustomerFetchType customerFetchType;


    @NotNull(message = "Minor flag is mandatory")
    private Boolean minor;

    @NotBlank(message = "Father's name is required")
    private String fathersName;

    private String guardianName;

    @NotBlank(message = "Mother's name is required")
    private String mothersName;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Size(min = 14, max = 14, message = "CKYC number must be exactly 14 digits")
    @Pattern(regexp = "\\d{14}", message = "CKYC number must contain only digits")
    @NotBlank
    private String ckycNo;

    @NotBlank
    private String nationality;

    @Valid
    private List<AddressDto> addressList = new ArrayList<>();
}
