package com.nharire.docketapp.app.model.dto;

import com.nharire.docketapp.app.model.PoliceStation;
import lombok.Data;

import java.io.Serializable;
@Data
public class OfficerDTO implements Serializable {

    private Long Id;
    private String firstName;
    private String lastName;
    private String rank;
    private String phoneNumber;
    private String email;
    private String officeLandLine;
    private String nationalId;
    private String description;
    private PoliceStationDTO policeStation;







}
