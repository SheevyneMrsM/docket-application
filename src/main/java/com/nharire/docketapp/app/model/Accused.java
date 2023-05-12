package com.nharire.docketapp.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accused")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accused {

    @Id
    private String nationalId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "next_of_kin_phone_number")
    private NextOfKin nextOfKin;

    @ManyToOne
    @JoinColumn(name = "crime_crime_id")
    private CrimeRegister crime;



}
