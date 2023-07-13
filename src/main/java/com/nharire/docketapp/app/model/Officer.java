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

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "rank", nullable = false)
    private String rank;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name ="office_land_line", nullable = false)
    private String officeLandLine;

    @Column(name = "national_id", nullable = false)
    private String nationalId;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "police_station_id", nullable = false)
    private PoliceStation policeStation;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;








}
