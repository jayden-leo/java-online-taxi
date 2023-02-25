package com.jayden.apipassenger.service;

import com.jayden.apipassenger.remote.VerifyCodeClient;
import com.jayden.internalcommon.constant.IdentityConstants;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.request.VerifyCodeDTO;
import com.jayden.internalcommon.response.NumberCodeResponse;
import com.jayden.internalcommon.response.TokenResponse;
import com.jayden.internalcommon.util.RedisPrefixUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerifyCodeService {

    @Autowired
    private VerifyCodeClient verifyCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult verifyCodeGet(String passengerPhone) {
        // 调用远程服务，获取验证码
        System.out.println("调用远程服务");
        VerifyCodeDTO verifyCodeDTO = new VerifyCodeDTO();
        verifyCodeDTO.setPassengerPhone(passengerPhone);
        Integer verifyCodeSize = 6;
        ResponseResult<NumberCodeResponse> responseResult = verifyCodeClient.verifyCodeGet(verifyCodeSize);
        int numberCode = responseResult.getData().getNumberCode();
        System.out.println("从service-verifycode服务得到的验证码是："+numberCode);

        // 将验证码存入redis
        System.out.println("存入redis");
        String passengerKey = RedisPrefixUtils.generatorKeyByPhone(passengerPhone, IdentityConstants.PASSENGER_IDENTITY);
        stringRedisTemplate.opsForValue().set(passengerKey,numberCode+"",2, TimeUnit.MINUTES);

        return ResponseResult.success();
    }


    public ResponseResult verifyCodeCheck(String passengerPhone, String verifyCode) {

        // 参数校验
        System.out.println("参数校验");

        // 判断redis和验证码的值
        System.out.println("判断redis和验证码");

        // 从数据库中查看是否有用户,是的话就插入,如果有就返回token

        // 生成token

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken("lskjdgapsig");
        return ResponseResult.success(tokenResponse);
    }
}
