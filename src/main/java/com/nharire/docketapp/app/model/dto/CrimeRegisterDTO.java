package com.nharire.docketapp.app.model.dto;

import com.nharire.docketapp.app.model.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
public class CrimeRegisterDTO  implements Serializable {

    private  Long crimeId;
    private Officer headId;
    private String dateOfOffense;
    private String underSection;
    private String  dateOfReport;
    private String  caseStatus;
    private String timeOfOffense;
    private String  description;
    private List<Accused> accused;
    private ComplainantDTO complainer;
    private PoliceStationDTO policeStation;
    private List<Report> reports;
    private WitnessDTO witness;


}
