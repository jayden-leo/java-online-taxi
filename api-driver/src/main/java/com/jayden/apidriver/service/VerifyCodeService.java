package com.jayden.apidriver.service;

import com.jayden.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class VerifyCodeService {

    public ResponseResult checkAndSendVerifyCode(String driverPhone){
        // 查询 service-driver-user,该手机号的司机是否存在

        // 获取验证码
        System.out.println(driverPhone);

        //  调用第三方发送验证码

        // 存入redis

        // 返回
        return ResponseResult.success();
    }

}
