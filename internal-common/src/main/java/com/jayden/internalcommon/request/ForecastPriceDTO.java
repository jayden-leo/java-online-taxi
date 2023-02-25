package com.jayden.internalcommon.request;

import lombok.Data;

@Data
public class ForecastPriceDTO {

    private String depLongitude;

    private String depLatitude;

    private String destLongitude;

    private String destLatitude;

    private String cityCode;

    private String vehicleType;
}
