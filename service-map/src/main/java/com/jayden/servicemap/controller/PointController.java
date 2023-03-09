package com.jayden.servicemap.controller;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.request.PointRequest;
import com.jayden.servicemap.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody PointRequest pointRequest){

        return pointService.upload(pointRequest);
    }
}
