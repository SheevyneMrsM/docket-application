package com.nharire.docketapp.app.model.dto;

import com.nharire.docketapp.app.model.CrimeRegister;
import lombok.Data;

import java.io.Serializable;
@Data
public class ReportDTO implements Serializable {
    private Long id;
    private CrimeRegisterDTO crime;

}
