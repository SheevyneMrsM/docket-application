package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.CrimeRegister;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class ReportResponse extends Response {

    private Long id;

    private CrimeRegister crimeRegister;

    private String reportDescription;


}
