package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
public class ComplainantResponse extends Response {

    private String nationalId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private Address address;

    private NextOfKin nextOfKin;

    private CrimeRegister crimeRegister;

    private List<Report> reports;

    private List<Witness> witness;

    private String getDateOfComplaint;

}
