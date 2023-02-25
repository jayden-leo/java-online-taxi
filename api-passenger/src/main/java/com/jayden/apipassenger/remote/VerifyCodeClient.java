package com.jayden.apipassenger.remote;

import com.jayden.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-verifycode")
public interface VerifyCodeClient {

    @GetMapping("/number-code/{size}")
    public ResponseResult verifyCodeGet(@PathVariable("size") Integer verifyCodeSize);
}
