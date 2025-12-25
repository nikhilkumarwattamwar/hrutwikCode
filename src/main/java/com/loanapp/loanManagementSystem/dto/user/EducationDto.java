package com.loanapp.loanManagementSystem.dto.user;

import com.loanapp.loanManagementSystem.enums.ExamPassed;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
