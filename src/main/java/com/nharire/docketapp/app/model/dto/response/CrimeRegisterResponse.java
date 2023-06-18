package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.*;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
@Data
public class CrimeRegisterResponse extends Response {

    private  Long crimeId;

    private Officer headId;

    private String dateOfOffense;

    private String underSection;

    private String dateOfReport;

    private String  caseStatus;

    private Time timeOfOffense;

    private String  descriptions;

    private List<Accused> accused;

    private Complainant complainer;

    private PoliceStation policeStations;

    private List<Report> reports;

    private List<Witness> witness;

}



