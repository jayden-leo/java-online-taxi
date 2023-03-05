package com.jayden.internalcommon.response;

import lombok.Data;

@Data
public class DriverUserExistsResponse {

    private String driverPhone;

    private int ifExists;
}
