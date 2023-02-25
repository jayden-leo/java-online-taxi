package com.jayden.apipassenger.remote;

import com.jayden.internalcommon.dto.PassengerUser;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.request.VerifyCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {

    @PostMapping("/user")
    public ResponseResult loginOrRegisterUser(@RequestBody VerifyCodeDTO verifyCodeDTO);

    @RequestMapping(method = RequestMethod.GET, value = "/user/{phone}")
    public ResponseResult<PassengerUser> getUserByPhone(@PathVariable("phone") String phone);
}
