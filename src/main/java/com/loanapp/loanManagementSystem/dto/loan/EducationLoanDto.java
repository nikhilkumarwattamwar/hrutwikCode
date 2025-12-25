package com.loanapp.loanManagementSystem.dto.loan;

import com.loanapp.loanManagementSystem.entities.user.CourseDetails;
import com.loanapp.loanManagementSystem.entities.user.EducationDetails;
import com.loanapp.loanManagementSystem.entities.loan.Loan;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationLoanDto extends LoanDto{


    @NotNull(message = "Course details are required")
    CourseDetails courseDetails;

    @NotNull(message = "Education details are required")
    EducationDetails educationDetails;

    @NotNull(message = "Course Fee are required")
    @DecimalMin(value = "0.0", message = "course fees cannot be negative")
    private Double courseFee;

    @NotNull(message = "Associated loan is required")
    private Loan loan;

}
