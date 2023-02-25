package com.jayden.apipassenger.service;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.jayden.internalcommon.constant.CommonStatusEnum;
import com.jayden.internalcommon.constant.TokenConstants;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.dto.TokenResult;
import com.jayden.internalcommon.response.TokenResponse;
import com.jayden.internalcommon.util.JwtUtils;
import com.jayden.internalcommon.util.RedisPrefixUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String refreshTokenSrc){
        // 解析 refreshToken
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if (tokenResult == null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getMessage());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();

        // 去读取redis中 的refreshToken
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(phone,identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);

        // 校验refreshToken
        if ((StringUtils.isBlank(refreshTokenRedis))  || (!refreshTokenSrc.trim().equals(refreshTokenRedis.trim()))){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getMessage());
        }

        // 生成双token
        String refreshToken = JwtUtils.generatorToken(phone,identity,TokenConstants.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(phone,identity,TokenConstants.ACCESS_TOKEN_TYPE);

        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(phone,identity,TokenConstants.ACCESS_TOKEN_TYPE);

        stringRedisTemplate.opsForValue().set(accessTokenKey , accessToken , 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey , refreshToken , 31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);

        return ResponseResult.success(tokenResponse);
    }
}
