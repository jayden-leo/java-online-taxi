package com.jayden.internalcommon.constant;

import lombok.Getter;

public enum CommonStatusEnum {

    /**
     * Token类提示：1100-1199
     */
    TOKEN_ERROR(1199,"token错误"),

    /**
     * 验证码错误提示：1000-1099
     */
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),

    /**
     * 成功响应
     */
    SUCCESS(1,"success"),

    /**
     * 失败响应
     */
    FAIL(0,"fail")
    ;
    @Getter
    private Integer code;
    @Getter
    private String message;

    CommonStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
