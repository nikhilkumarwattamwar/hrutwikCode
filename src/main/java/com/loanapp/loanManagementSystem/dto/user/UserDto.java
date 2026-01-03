package com.loanapp.loanManagementSystem.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.loanapp.loanManagementSystem.enums.CustomerFetchType;
import com.loanapp.loanManagementSystem.enums.CustomerType;
import com.loanapp.loanManagementSystem.enums.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    UUID id;

    private String name;


    @Email(message = "Invalid email address. Please enter valid email")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private CustomerType customerType;


    private CustomerFetchType customerFetchType;

    private Boolean minor;

    private String fathersName;

    private String guardianName;

    private String mothersName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Size(min = 14, max = 14, message = "CKYC number must be exactly 14 digits")
    @Pattern(regexp = "\\d{14}", message = "CKYC number must contain only digits")
    private String ckycNo;

    private String nationality;

    @Valid
    private List<AddressDto> addressList = new ArrayList<>();

    @JsonIgnore
    private Role role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
