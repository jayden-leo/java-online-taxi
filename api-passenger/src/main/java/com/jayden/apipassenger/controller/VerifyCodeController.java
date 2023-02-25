package com.jayden.apipassenger.controller;

import com.jayden.apipassenger.service.VerifyCodeService;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.request.VerifyCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyCodeController {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @GetMapping("/verifycode-get")
    public ResponseResult verifyCodeGet(@RequestBody VerifyCodeDTO verifycodeDTO){

        String passengerPhone = verifycodeDTO.getPassengerPhone();

        return verifyCodeService.verifyCodeGet(passengerPhone);

    }
}
