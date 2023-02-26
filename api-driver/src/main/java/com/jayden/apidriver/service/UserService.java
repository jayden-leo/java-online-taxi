package com.jayden.apidriver.service;

import com.jayden.apidriver.remote.ServiceDriverUserClient;
import com.jayden.internalcommon.dto.DriverUser;
import com.jayden.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult updateUser(DriverUser driverUser) {
        ResponseResult responseResult = serviceDriverUserClient.updateUser(driverUser);
        return responseResult;
    }
}
