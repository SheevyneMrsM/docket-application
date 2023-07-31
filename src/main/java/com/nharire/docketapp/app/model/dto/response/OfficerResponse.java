package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.PoliceStation;
import lombok.Data;

@Data
public class OfficerResponse extends Response {
    private String nationalId;
    private Long id;
    private String firstName;
    private String lastName;
    private String rank;
    private String phoneNumber;
    private String email;
    private String officeLandLine;
    private String description;
    private PoliceStation policeStation;
    private Address address;
}
