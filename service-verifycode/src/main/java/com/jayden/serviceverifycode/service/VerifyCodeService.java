package com.jayden.serviceverifycode.service;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.response.NumberCodeResponse;
import org.springframework.stereotype.Service;

@Service
public class VerifyCodeService {

    public ResponseResult generateCode(Integer size) {

        double random = (Math.random()*9+1)*Math.pow(10,size-1);
        int numberCode = (int) random;
        System.out.println("生成的验证码是："+numberCode);
        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
        numberCodeResponse.setNumberCode(numberCode);
        return ResponseResult.success(numberCodeResponse);
    }

    public static void main(String[] args){
        double random = (Math.random()*9+1)*100000;
        System.out.println(random);
    }
}
