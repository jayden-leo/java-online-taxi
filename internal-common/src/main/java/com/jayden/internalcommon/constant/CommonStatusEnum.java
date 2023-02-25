package com.jayden.internalcommon.constant;

import lombok.Getter;

public enum CommonStatusEnum {

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
