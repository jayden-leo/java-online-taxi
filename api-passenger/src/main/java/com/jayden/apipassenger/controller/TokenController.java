package com.jayden.apipassenger.controller;


import com.jayden.apipassenger.service.TokenService;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){

        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的 refreshToken："+refreshTokenSrc);

        return tokenService.refreshToken(refreshTokenSrc);

    }
}
