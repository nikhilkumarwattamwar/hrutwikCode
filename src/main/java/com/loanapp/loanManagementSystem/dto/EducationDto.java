package com.loanapp.loanManagementSystem.dto;

import com.loanapp.loanManagementSystem.entities.User;
import com.loanapp.loanManagementSystem.enums.ExamPassed;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {
    private ExamPassed examPassed;

    private String institution;

    private String boardOrUniversity;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private Double marksOrCgpa;

}
