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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "street_address", nullable = false)
     private String streetAddress;

    @Column(name = "suburb", nullable = false)
     private String suburb;

    @Column(name = "city", nullable = false)
     private String city;

    @Column(name = "province", nullable = false)
     private String province;

    @Column(name = "latitude", nullable = false)
     private String latitude;

    @Column(name = "longitude", nullable = false)
     private String longitude;

}
