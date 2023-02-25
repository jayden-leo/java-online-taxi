package com.jayden.servicepassengeruser.controller;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.request.VerifyCodeDTO;
import com.jayden.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegisterUser(@RequestBody VerifyCodeDTO verifyCodeDTO){
        String passengerPhone = verifyCodeDTO.getPassengerPhone();
        return userService.loginOrRegisterUser(passengerPhone);
    }
}
