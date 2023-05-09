package com.nharire.docketapp.app.model.dto;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.Officer;
import com.nharire.docketapp.app.model.PoliceStation;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Data
public class CrimeRegisterDTO  implements Serializable {

    private  Long crimeId;
    private Officer headId;
    private Date dateOfOffense;
    private String underSection;
    private Date  dateOfReport;
    private String  caseStatus;
    private Time timeOfOffense;
    private String  description;
    private AccusedDTO accusedId;
    private ComplainantDTO complainer;
    private PoliceStationDTO policeStation;


}
