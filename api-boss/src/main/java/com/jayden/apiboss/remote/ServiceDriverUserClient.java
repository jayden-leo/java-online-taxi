package com.jayden.apiboss.remote;

import com.jayden.internalcommon.dto.Car;
import com.jayden.internalcommon.dto.DriverCarBindingRelationship;
import com.jayden.internalcommon.dto.DriverUser;
import com.jayden.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @PostMapping("/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser);

    @PutMapping("/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);


    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.POST,value = "/driver-car-binding-relationship/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);

    @RequestMapping(method = RequestMethod.POST,value = "/driver-car-binding-relationship/unbind")
    ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship);


}
