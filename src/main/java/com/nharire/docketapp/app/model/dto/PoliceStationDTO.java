package com.nharire.docketapp.app.model.dto;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Officer;
import com.nharire.docketapp.app.model.Review;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PoliceStationDTO implements Serializable {

    private Long id;
    private String policeStationName;
    private AddressDTO address;
    private String phoneNumber;
    private String email;
    private String officerNationalId;
    private List<Review> addReview;




}
