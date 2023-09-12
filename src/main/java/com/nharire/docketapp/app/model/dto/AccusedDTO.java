package com.nharire.docketapp.app.model.dto;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.NextOfKin;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AccusedDTO implements Serializable  {

    private Long crimeId;
    private String nationalId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private AddressDTO address;
    private NextOfKinDTO nextOfKin;
    private String dateReported;



}
