package com.loanapp.loanManagementSystem.entities.loan;

import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.enums.LoanType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "loanCategory", discriminatorType = DiscriminatorType.STRING)
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
   private UUID loanId;

    @Column(name = "loanType")
    @Enumerated(EnumType.STRING)
    private LoanType loanType;


    private Double loanAmount;

    private Double interestRate;

    private Integer tenure;

    @Column(name = "isActive" ,nullable = false)
    private boolean isActive=true;

    @ManyToOne
     @JoinColumn(name = "userId" ,nullable = false)
    User user;

}
