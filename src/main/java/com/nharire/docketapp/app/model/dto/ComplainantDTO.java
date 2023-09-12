package com.nharire.docketapp.app.model.dto;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.Report;
import com.nharire.docketapp.app.model.Witness;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ComplainantDTO implements Serializable {

    private Long crimeId;
    private String nationalId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Address address;
    private String nextOfKinNationalId;
    private List<ReportDTO> reports;
    private List<Witness> witness;
    private String getDateOfComplaint;





}
