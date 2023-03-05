package com.jayden.internalcommon.request;

import lombok.Data;

@Data
public class VerifyCodeDTO {
    private String passengerPhone;

    private String driverPhone;

    private String verifyCode;
}
