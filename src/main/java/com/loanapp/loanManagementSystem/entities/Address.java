package com.loanapp.loanManagementSystem.entities;

import com.loanapp.loanManagementSystem.enums.AddressType;
import com.loanapp.loanManagementSystem.enums.Country;
import com.loanapp.loanManagementSystem.enums.HouseOwnership;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Address")
public class Address {

    @Id
    @Column(name = "addressId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AddressType type;

    private String landmark;
    private String district;
    private String city;
    private String state;

    @Enumerated(EnumType.STRING)
    private Country country;
    private String pinCode;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private HouseOwnership houseOwnedBy;

    @NotNull
    private Integer yearsAtCurrentResidence;

    @Column(name = "isActive" ,nullable = false)
    private boolean isActive=true;

    @PrePersist
    public void onCreate() {
        this.isActive=true;
    }


}
