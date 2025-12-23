package com.loanapp.loanManagementSystem.entities;

import com.loanapp.loanManagementSystem.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personalDetailId")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
     private MaritalStatus maritalStatus;

    @Column(name = "panNumber", nullable = false)
   private String panCardNumber;

    @Column(name = "aadharNumber", nullable = false)
    private String aadharNumber;

    @Column(name = "passportNumber", nullable = true)
    private String passportNumber;

    @Column(name = "voterIdNumber", nullable = true)
    private String voterIdNumber;

    @Column(name = "disability", nullable = false)
    private Disability disability;

    @Enumerated(EnumType.STRING)
     private Constitution constitution;

    @Enumerated(EnumType.STRING)
    private Religion religion;

    @Enumerated(EnumType.STRING)
     private Education education;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "isActive" ,nullable = false)
    private boolean isActive=true;

    @PrePersist
    public void onCreate() {
        this.isActive=true;
    }

}
