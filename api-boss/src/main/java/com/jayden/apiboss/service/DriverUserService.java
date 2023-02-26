package com.jayden.apiboss.service;

import com.jayden.apiboss.remote.ServiceDriverUserClient;
import com.jayden.internalcommon.dto.DriverUser;
import com.jayden.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverUserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addDriverUser(DriverUser driverUser){
        ResponseResult responseResult = serviceDriverUserClient.addDriverUser(driverUser);

        return responseResult;
    }

    public ResponseResult updateDriverUser(DriverUser driverUser){
        ResponseResult responseResult = serviceDriverUserClient.updateDriverUser(driverUser);

        return responseResult;
    }


}
