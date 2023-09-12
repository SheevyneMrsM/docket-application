package com.nharire.docketapp.app.model.dto;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.PoliceStation;
import lombok.Data;

import java.io.Serializable;
@Data
public class OfficerDTO implements Serializable {

    private String nationalId;
    private Long id;
    private String firstName;
    private String lastName;
    private String rank;
    private String phoneNumber;
    private String email;
    private String officeLandLine;
    private String description;
    private Address address;
    private String policeStationId;







}
