package com.nharire.docketapp.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "complainant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complainant {

    @Id
    private String nationalId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name ="last_name", nullable = false)
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


    @Column(name = "crime_crime_id", nullable = false)
    private Long crimeId;

    @OneToMany
    @JoinColumn(name = "report", nullable = false)
    private List<Report> reports;

    @OneToMany
    @JoinColumn(name = "witness_id", nullable = false)
    private List<Witness> witness;

    private String getDateOfComplaint;

}
