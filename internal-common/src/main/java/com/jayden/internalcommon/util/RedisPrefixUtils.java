package com.jayden.internalcommon.util;

public class RedisPrefixUtils {
    /**
     * 乘客验证码码的key前缀
     */
    public static String verificationCodePrefix = "verification-code-";


    /**
     * 根据手机号，生成key
     * @param phone
     * @param identity  有可能是司机
     * @return
     */
    public static String generatorKeyByPhone(String phone, String identity){
        return verificationCodePrefix + identity+ "-" + phone;
    }
}
