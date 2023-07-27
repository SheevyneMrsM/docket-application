package com.nharire.docketapp.app.model.dto.response;

import com.nharire.docketapp.app.common.Response;
import com.nharire.docketapp.app.model.PoliceStation;
import lombok.Data;

@Data
public class ReviewResponse extends Response {
    private Long id;
    private String body;
    private PoliceStation policeStation;
}
