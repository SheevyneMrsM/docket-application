package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.dto.AddressDTO;
import com.nharire.docketapp.app.model.dto.NextOfKinDTO;
import lombok.Data;

@Data
public class AccusedResponse extends Response {

    private Long crimeId;

    private String nationalId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private Address address;

    private NextOfKinDTO nextOfKin;

    private CrimeRegister crimeRegister;

    private String dateReported;


}
