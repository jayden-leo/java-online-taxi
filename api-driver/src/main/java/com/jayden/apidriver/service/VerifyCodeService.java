package com.jayden.apidriver.service;

import com.alibaba.nacos.api.utils.StringUtils;
import com.jayden.apidriver.remote.ServiceDriverUserClient;
import com.jayden.apidriver.remote.ServiceVerifyCodeClient;
import com.jayden.internalcommon.constant.CommonStatusEnum;
import com.jayden.internalcommon.constant.DriverCarConstants;
import com.jayden.internalcommon.constant.IdentityConstants;
import com.jayden.internalcommon.constant.TokenConstants;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.response.DriverUserExistsResponse;
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
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceVerifyCodeClient serviceVerifyCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult checkAndSendVerifyCode(String driverPhone){
        // 查询 service-driver-user,该手机号的司机是否存在
        ResponseResult<DriverUserExistsResponse> responseResult = serviceDriverUserClient.checkDriver(driverPhone);
        DriverUserExistsResponse data = responseResult.getData();
        int ifExists = data.getIfExists();
        if (ifExists== DriverCarConstants.DRIVER_NOT_EXISTS){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXIST.getCode(),CommonStatusEnum.DRIVER_NOT_EXIST.getMessage());
        }
        System.out.println(driverPhone+" 的司机存在 ");

        // 获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResult = serviceVerifyCodeClient.verifyCodeGet(6);
        NumberCodeResponse numberCodeResponse = numberCodeResult.getData();
        Integer numberCode = numberCodeResponse.getNumberCode();
        System.out.println(numberCode);
        //  调用第三方发送验证码: 阿里短信服务, 腾讯, 华信, 容联等

        // 存入redis
        String passengerKey = RedisPrefixUtils.generatorKeyByPhone(driverPhone, IdentityConstants.DRIVER_IDENTITY);
        stringRedisTemplate.opsForValue().set(passengerKey,numberCode+"",2, TimeUnit.MINUTES);

        // 返回
        return ResponseResult.success();
    }

    public ResponseResult verifyCodeCheck(String driverPhone, String verifyCode) {

        // 判断redis和验证码的值
        System.out.println("判断redis和验证码");
        // 生成key
        String key = RedisPrefixUtils.generatorKeyByPhone(driverPhone,IdentityConstants.DRIVER_IDENTITY) ;
        String codeRedis = stringRedisTemplate.opsForValue().get(key);


        if (StringUtils.isBlank(codeRedis)||!codeRedis.trim().equals(verifyCode)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
        }

        // 生成token
        // 颁发令牌，不应该用魔法值，用常量
        String accessToken = JwtUtils.generatorToken(driverPhone, IdentityConstants.DRIVER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(driverPhone, IdentityConstants.DRIVER_IDENTITY , TokenConstants.REFRESH_TOKEN_TYPE);
        // 将token存到redis当中
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(driverPhone , IdentityConstants.DRIVER_IDENTITY , TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey , accessToken , 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(driverPhone , IdentityConstants.DRIVER_IDENTITY , TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey , refreshToken , 31, TimeUnit.DAYS);

        // 响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }

}
