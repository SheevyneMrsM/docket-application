package com.nharire.docketapp.app.model.dto;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.NextOfKin;
import lombok.Data;

import java.io.Serializable;
@Data
public class WitnessDTO implements Serializable {

    private String nationalId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private AddressDTO address;
    private NextOfKinDTO nextOfKin;
    private CrimeRegisterDTO crime;

}
