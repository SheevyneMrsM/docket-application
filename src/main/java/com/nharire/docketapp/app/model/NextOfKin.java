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
    @Column(name = "national_id")
    private String nationalId ;

    @Column(name = "first_name")
    private String firstName ;

    @Column(name = "last_name")
    private String surname;


    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "relationship")
    private String relationship;

    @ManyToOne
    @JoinColumn(name = "accused_national_id")
    private Accused accused;

    @OneToMany
    @JoinColumn(name = "complainant_national_id")
    private List<Complainant> complainant;

    private String addAddressDetails;

}
