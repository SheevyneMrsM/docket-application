package com.nharire.docketapp.app.model.dto;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.Address;
import lombok.Data;

import java.io.Serializable;
@Data
public class NextOfKinDTO implements Serializable {

    private String nationalId;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private String email;
    private Address address;
    private String relationship;
    private Accused accused;




}
