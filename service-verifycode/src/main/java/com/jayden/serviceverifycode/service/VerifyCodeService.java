package com.jayden.serviceverifycode.service;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.response.NumberCodeResponse;
import org.springframework.stereotype.Service;

@Service
public class VerifyCodeService {

    public ResponseResult generateCode(Integer size) {
        System.out.println(size);
        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
        numberCodeResponse.setNumberCode(size);
        return ResponseResult.success(numberCodeResponse);
    }
}
