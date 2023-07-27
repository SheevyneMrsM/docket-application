package com.nharire.docketapp.app.model.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class ReviewDTO implements Serializable {


    private Long id;
    private String body;
    private Long policeStationId;


}
