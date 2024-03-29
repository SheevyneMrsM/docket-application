package com.nharire.docketapp.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "officer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Officer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "rank")
    private String rank;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name ="office_land_line")
    private String officeLandLine;


    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "description")
    private String description;

    @Column(name = "police_station_id")
    private String policeStationId;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;








}
