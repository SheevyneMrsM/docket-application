package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.NextOfKin;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Data
public class WitnessResponse extends Response {

    private String nationalId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private Address address;

    private NextOfKin nextOfKin;

    private CrimeRegister crime;

    private Complainant complainant;
}



