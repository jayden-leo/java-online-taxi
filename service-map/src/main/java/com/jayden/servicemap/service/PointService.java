package com.jayden.servicemap.service;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.request.PointRequest;
import com.jayden.servicemap.remote.PointClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    @Autowired
    private PointClient pointClient;

    public ResponseResult upload(PointRequest pointRequest){

        return  pointClient.upload(pointRequest);
    }
}
