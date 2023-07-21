package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Officer;
import com.nharire.docketapp.app.model.Review;
import lombok.Data;

import java.util.List;
@Data
public class PoliceStationResponse extends Response {

    private Long id;
    private String policeStationName;
    private Address address;
    private String phoneNumber;
    private String email;
    private Officer officerInCharge;
    private List<Review> addReview;

}
