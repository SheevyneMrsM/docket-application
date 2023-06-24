package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.dto.AddressDTO;
import com.nharire.docketapp.app.model.dto.NextOfKinDTO;
import lombok.Data;

@Data
public class AccusedResponse extends Response {

    private String crimeId;

    private String nationalId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private AddressDTO address;

    private NextOfKinDTO nextOfKin;

    private CrimeRegister crimeRegister;


}
