package com.jayden.apidriver.service;

import com.jayden.apidriver.remote.ServiceDriverUserClient;
import com.jayden.apidriver.remote.ServiceVerifyCodeClient;
import com.jayden.internalcommon.constant.CommonStatusEnum;
import com.jayden.internalcommon.constant.DriverCarConstants;
import com.jayden.internalcommon.dto.ResponseResult;
import com.jayden.internalcommon.response.DriverUserExistsResponse;
import com.jayden.internalcommon.response.NumberCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyCodeService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceVerifyCodeClient serviceVerifyCodeClient;

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

        //  调用第三方发送验证码

        // 存入redis

        // 返回
        return ResponseResult.success();
    }

}
