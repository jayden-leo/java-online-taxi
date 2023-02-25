package com.jayden.serviceverifycode.controller;

import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.serviceverifycode.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyCodeController {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @GetMapping("/number-code/{size}")
    public ResponseResult verifyCodeGet(@PathVariable("size") Integer size) {


        return verifyCodeService.generateCode(size);

    }
}
