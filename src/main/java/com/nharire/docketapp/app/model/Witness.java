package com.nharire.docketapp.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "witness")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Witness {

    @Id
    private String nationalId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id",nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "next_of_kin_phone_number", nullable = false)
    private NextOfKin nextOfKin;

    @ManyToOne
    @JoinColumn(name = "crime_crime_id", nullable = false)
    private CrimeRegister crime;

    @ManyToOne
    @JoinColumn(name = "complainant_id", nullable = false)
    private Complainant complainant;
}
