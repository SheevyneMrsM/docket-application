package com.nharire.docketapp.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @Column(name = "street_address")
     private String streetAddress;

    @Column(name = "suburb")
     private String suburb;

    @Column(name = "city")
     private String city;

    @Column(name = "province")
     private String province;

    @Column(name = "latitude")
     private String latitude;

    @Column(name = "longitude")
     private String longitude;

}
