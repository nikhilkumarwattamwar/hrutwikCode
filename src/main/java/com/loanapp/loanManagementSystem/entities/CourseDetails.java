package com.loanapp.loanManagementSystem.entities;

import com.loanapp.loanManagementSystem.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courseDetails")
public class CourseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    Integer id;

    @Enumerated(EnumType.STRING)
    EntranceExamGiven examGiven;

    @Min(value = 0, message = "Exam score cannot be negative")
    @Max(value = 100, message = "Exam score cannot be more than 100")
    Integer examScore;

    @Enumerated(EnumType.STRING)
    AdmissionStatus status;

    String collegeName;

    String city;
    String state;


    String pinCode;

    @Enumerated(EnumType.STRING)
    Country country;

    String courseName;

    @Enumerated(EnumType.STRING)
    CourseAppliedFor applied;

    @Enumerated(EnumType.STRING)
    CourseCategory category;

    @Enumerated(EnumType.STRING)
    CourseType courseType;

    LocalDate courseBegins;
    LocalDate courseEnds;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "isActive" ,nullable = false)
    private boolean isActive=true;


    @PrePersist
    public void onCreate() {
        this.isActive=true;
    }


}
