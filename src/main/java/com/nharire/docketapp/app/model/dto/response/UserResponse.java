package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Report;
import com.nharire.docketapp.app.model.Review;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
public class UserResponse extends Response {

    private String nationalId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String password;

    private Report report;

    private List<Review> reviews;

    private Address address;



}
