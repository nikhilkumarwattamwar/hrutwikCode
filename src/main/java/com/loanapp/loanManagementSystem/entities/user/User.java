package com.loanapp.loanManagementSystem.entities.user;

import com.loanapp.loanManagementSystem.entities.loan.Loan;
import com.loanapp.loanManagementSystem.enums.CustomerFetchType;
import com.loanapp.loanManagementSystem.enums.CustomerType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name cannot be empty.")
    @Column(name = "Name", nullable = false)
    private String name;


    @Column(name = "Email", unique = true, nullable = false)
    @Email(message = "Invalid email address. Please enter valid email")
    @NotBlank(message = "Please enter your email ID.")
    private String email;


    @Column(name = "Mobile_Number", unique = true, nullable = false)
    private String mobileNumber;


    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @Enumerated(EnumType.STRING)
    private CustomerFetchType customerFetchType;

    @Column(name = "Created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "Updated_at")
    private LocalDateTime updatedAt;

    private Boolean minor;
    private String fathersName;
    private String guardianName;
    private String mothersName;
    private LocalDate dateOfBirth;
    private String ckycNo;
    private String nationality;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Address> addressList= new ArrayList<>();


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private PersonalDetails personalDetails;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private CourseDetails courseDetails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<EducationDetails> educationDetailsList= new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Loan> loanList;

    @Column(name = "isActive" ,nullable = false)
    private boolean isActive=true;

    public void setAddressDetail(Address address){
        addressList.add(address);
        address.setUser(this);
    }

    public void setEducationDetails(EducationDetails details){
                educationDetailsList.add(details);
                details.setUser(this);
    }


    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isActive=true;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
