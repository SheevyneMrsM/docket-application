package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.Complainant;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
public class AddressResponse extends Response {

    private  Long id;

    private String streetAddress;

    private String suburb;

    private String city;

    private String province;

    private String latitude;

    private String longitude;

    private Complainant complainant;

}



