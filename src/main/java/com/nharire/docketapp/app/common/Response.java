package com.nharire.docketapp.app.common;

import lombok.Data;

@Data
public class Response {

    private int responseCode;
    private String message;
    private String code;
    private String description;
}
