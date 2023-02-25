package com.jayden.apipassenger.service;

import com.alibaba.nacos.api.utils.StringUtils;
import com.jayden.apipassenger.remote.ServicePassengerUserClient;
import com.jayden.apipassenger.remote.ServiceVerifyCodeClient;
import com.jayden.internalcommon.constant.CommonStatusEnum;
import com.jayden.internalcommon.constant.IdentityConstants;
import com.jayden.internalcommon.constant.TokenConstants;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.request.VerifyCodeDTO;
import com.jayden.internalcommon.response.NumberCodeResponse;
import com.jayden.internalcommon.response.TokenResponse;
import com.jayden.internalcommon.util.JwtUtils;
import com.jayden.internalcommon.util.RedisPrefixUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeService {

    @Autowired
    private ServiceVerifyCodeClient verifyCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult verifyCodeGet(String passengerPhone) {
        // 调用远程服务，获取验证码
        System.out.println("调用远程服务");
        VerifyCodeDTO verifyCodeDTO = new VerifyCodeDTO();
        verifyCodeDTO.setPassengerPhone(passengerPhone);
        Integer verifyCodeSize = 6;
        ResponseResult<NumberCodeResponse> responseResult = verifyCodeClient.verifyCodeGet(verifyCodeSize);
        Integer numberCode = responseResult.getData().getNumberCode();
        verifyCodeDTO.setVerifyCode(numberCode.toString());
        System.out.println("从service-verifycode服务得到的验证码是："+numberCode);

        // 将验证码存入redis
        System.out.println("存入redis");
        String passengerKey = RedisPrefixUtils.generatorKeyByPhone(passengerPhone, IdentityConstants.PASSENGER_IDENTITY);
        stringRedisTemplate.opsForValue().set(passengerKey,numberCode+"",2, TimeUnit.MINUTES);


        return ResponseResult.success(verifyCodeDTO);
    }

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;


    public ResponseResult verifyCodeCheck(String passengerPhone, String verifyCode) {

        // 判断redis和验证码的值
        System.out.println("判断redis和验证码");
        // 生成key
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone,IdentityConstants.PASSENGER_IDENTITY) ;
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(codeRedis)||!codeRedis.trim().equals(verifyCode)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
        }

        // 从数据库中查看是否有用户,是的话就插入,如果有就什么都不执行,没有就注册
        VerifyCodeDTO verifyCodeDTO = new VerifyCodeDTO();
        verifyCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegisterUser(verifyCodeDTO);

        // 生成token
        // 颁发令牌，不应该用魔法值，用常量
        String accessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY , TokenConstants.REFRESH_TOKEN_TYPE);
        // 将token存到redis当中
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone , IdentityConstants.PASSENGER_IDENTITY , TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey , accessToken , 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone , IdentityConstants.PASSENGER_IDENTITY , TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey , refreshToken , 31, TimeUnit.DAYS);

        // 响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
