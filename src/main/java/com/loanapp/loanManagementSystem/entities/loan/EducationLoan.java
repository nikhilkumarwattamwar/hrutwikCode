package com.loanapp.loanManagementSystem.entities.loan;

import com.loanapp.loanManagementSystem.entities.user.CourseDetails;
import com.loanapp.loanManagementSystem.entities.user.EducationDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("EDUCATION")
public class EducationLoan extends Loan {

    @OneToOne
    @JoinColumn(name = "courseDetailId", nullable = true)
    CourseDetails courseDetails;

    @OneToOne
    @JoinColumn(name = "educationDetailId", nullable = true)
    EducationDetails educationDetails;

    private Double courseFee;

}
