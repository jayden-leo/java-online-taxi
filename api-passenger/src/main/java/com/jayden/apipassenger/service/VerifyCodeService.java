package com.jayden.apipassenger.service;

import com.jayden.apipassenger.remote.VerifyCodeClient;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.request.VerifyCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyCodeService {

    @Autowired
    private VerifyCodeClient verifyCodeClient;

    public ResponseResult verifyCodeGet(String passengerPhone) {
        // 调用远程服务，获取验证码
        System.out.println("调用远程服务");
        VerifyCodeDTO verifyCodeDTO = new VerifyCodeDTO();
        verifyCodeDTO.setPassengerPhone(passengerPhone);
        Integer verifyCodeSize = 6;
        ResponseResult responseResult = verifyCodeClient.verifyCodeGet(verifyCodeSize);
        System.out.println(responseResult.getMessage());



        // 将验证码存入redis
        System.out.println("存入redis");

        return ResponseResult.success();
    }


}
