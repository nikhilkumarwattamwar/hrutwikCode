package com.loanapp.loanManagementSystem.entities;

import com.loanapp.loanManagementSystem.enums.ExamPassed;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "educationDetails", uniqueConstraints =@UniqueConstraint(columnNames ={"userId","examPassed"} ))
public class EducationDetails {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "educationId")
Integer id;


    @Column(name = "examPassed")
    @Enumerated(EnumType.STRING)
    ExamPassed examPassed;

    @Column(name = "institution")
    String institution;

    @Column(name = "boardOrUniversity")
    String boardOrUniversity;

    @Column(name = "marksOrCgpa")
    Double marksOrCgpa;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @Column(name = "isActive", nullable = false)
    private boolean isActive = true;

    @PrePersist
    public void onCreate() {
        this.isActive = true;
    }

}
