package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Complainant;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class NextOfKinResponse extends Response {

    private String nationalId;

    private String firstName;

    private String surname;

    private String phoneNumber;

    private String email;

    private Address address;

    private String relationship;

    private Accused accused;

    private List<Complainant> complainant;

    private String addAddressDetails;

}


