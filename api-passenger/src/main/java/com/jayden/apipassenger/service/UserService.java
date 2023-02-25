package com.jayden.apipassenger.service;

import com.jayden.apipassenger.remote.ServicePassengerUserClient;
import com.jayden.internalcommon.constant.CommonStatusEnum;
import com.jayden.internalcommon.dto.PassengerUser;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.dto.TokenResult;
import com.jayden.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken) {
        log.info("accessToken:" + accessToken);
        // 解析accessToken，拿到手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        if (tokenResult==null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getMessage());
        }
        String phone = tokenResult.getPhone();
        log.info("手机号：" + phone);

        // 根据手机号查询用户信息
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);

        return ResponseResult.success(userByPhone.getData());
    }
}