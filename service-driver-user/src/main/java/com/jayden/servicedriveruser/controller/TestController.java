package com.jayden.servicedriveruser.controller;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DriverUserService driverUserService;

    @GetMapping("/test")
    public String test(){
        return "service-driver-user is running!!";
    }

    @GetMapping("/test-get-driver-user")
    public ResponseResult testGetUser(){
        return driverUserService.testGetDriverUser();
    }
}
