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
    @Column(name = "national_Id", nullable = false)
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
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany
    @JoinColumn(name = "next_of_kin_phone_number", nullable = false)
    private List<NextOfKin> nextOfKin;

    @ManyToOne
    @JoinColumn(name = "crime_crime_id", nullable = false)
    private CrimeRegister crime;

    private String dateReported;


}
