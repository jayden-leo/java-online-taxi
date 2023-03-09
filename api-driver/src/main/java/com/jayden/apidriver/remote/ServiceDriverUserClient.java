package com.jayden.apidriver.remote;

import com.jayden.internalcommon.dto.Car;
import com.jayden.internalcommon.dto.DriverUser;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.response.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser);


    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> checkDriver(@PathVariable("driverPhone") String driverPhone);

    @RequestMapping(method = RequestMethod.GET, value = "/car")
    public ResponseResult<Car> getCarById(@RequestParam Long carId);
}
