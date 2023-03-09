package com.jayden.internalcommon.request;

import lombok.Data;

@Data
public class PointRequest {

    private String tid;

    private String trid;

    private PointDTO[] points;

}
