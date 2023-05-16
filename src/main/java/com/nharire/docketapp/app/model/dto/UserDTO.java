package com.nharire.docketapp.app.model.dto;

import com.nharire.docketapp.app.model.Report;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {

    private String nationalId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private ReportDTO report;
    private List<ReviewDTO> review;

}
