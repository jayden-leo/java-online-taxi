package com.jayden.internalcommon.constant;

import lombok.Getter;

public enum CommonStatusEnum {

    /**
     * 计价规则:1300-1399
     */
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),

    PRICE_RULE_EXISTS(1301,"计价规则已存在，不允许添加"),

    PRICE_RULE_NOT_EDIT(1302,"计价规则没有变化"),

    PRICE_RULE_CHANGED(1303,"计价规则有变化"),

    /**
     * 用户提示：1200-1299
     */
    USER_NOT_EXISTS(1200,"当前用户不存在"),

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
