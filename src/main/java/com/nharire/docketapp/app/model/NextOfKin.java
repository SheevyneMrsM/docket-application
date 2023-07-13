package com.nharire.docketapp.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "next_of_kin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NextOfKin {
    @Id
    @Column(name = "national_id", nullable = false)
    private String nationalId ;

    @Column(name = "first_name", nullable = false)
    private String firstName ;

    @Column(name = "last_name", nullable = false)
    private String surname;


    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "relationship", nullable = false)
    private String relationship;

    @ManyToOne
    @JoinColumn(name = "accused_national_id", nullable = false)
    private Accused accused;

    @OneToMany
    @JoinColumn(name = "complainant_national_id", nullable = false)
    private List<Complainant> complainant;

    private String addAddressDetails;

}
