package com.nharire.docketapp.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name ="police_stations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceStation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "police_station_name")
    private String policeStationName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "officer_in_charge_id")
    private Officer officerInCharge;

    @Column(name = "reviews")
    private List<Review> reviews;


}
