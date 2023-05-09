package com.nharire.docketapp.app.model.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class AddressDTO implements Serializable {

    private String streetAddress;
    private String suburb;
    private String city;
    private String province;
    private String latitude;
    private String longitude;



}
