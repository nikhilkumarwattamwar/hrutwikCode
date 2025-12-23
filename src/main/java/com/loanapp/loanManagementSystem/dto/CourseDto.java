package com.loanapp.loanManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.loanapp.loanManagementSystem.enums.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    EntranceExamGiven examGiven;

    @Min(0)
    @Max(100)
    Integer examScore;

    AdmissionStatus status;

    String collegeName;
    String city;
    String state;

    @Pattern(regexp = "\\d{6}", message = "Pin code must be 6 digits")
    @Size(min = 6, max = 6)
    String pinCode;

    Country country;
    String courseName;
    CourseAppliedFor applied;
    CourseCategory category;
    CourseType courseType;

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate courseBegins;

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate courseEnds;

}
