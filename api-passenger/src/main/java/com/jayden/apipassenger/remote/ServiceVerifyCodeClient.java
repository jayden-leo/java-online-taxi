package com.jayden.apipassenger.remote;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-verifycode")
public interface ServiceVerifyCodeClient {

    @GetMapping("/number-code/{size}")
    public ResponseResult<NumberCodeResponse> verifyCodeGet(@PathVariable("size") Integer verifyCodeSize);
}
