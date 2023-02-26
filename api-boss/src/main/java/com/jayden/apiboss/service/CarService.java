package com.jayden.apiboss.service;

import com.jayden.apiboss.remote.ServiceDriverUserClient;
import com.jayden.internalcommon.dto.Car;
import com.jayden.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CarService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addCar(Car car) {
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        serviceDriverUserClient.addCar(car);
        return ResponseResult.success("");
    }
}
