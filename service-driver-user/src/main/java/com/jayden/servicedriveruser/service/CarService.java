package com.jayden.servicedriveruser.service;

import com.jayden.internalcommon.dto.Car;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.response.TerminalResponse;
import com.jayden.servicedriveruser.mapper.CarMapper;
import com.jayden.servicedriveruser.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult addCar(Car car) {
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        // 获得此车辆 对应的 tid
        ResponseResult<TerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicleNo());
        String tid = responseResult.getData().getTid();
        car.setTid(tid);
        carMapper.insert(car);
        return ResponseResult.success("");
    }
}
