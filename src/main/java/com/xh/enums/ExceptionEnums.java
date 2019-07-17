package com.xh.enums;

import com.xh.constant.MyExceptionConstants;

/**
 * @author xiaohe
 * @version V1.0.0
 */
public enum ExceptionEnums implements ResultEnum {

    /**
     * 定义返回错误码 和错误信息
     */
    CUSTOM_ERROR(1000,"未知错误"),
    /**
     * 用户名密码不能为空
     */
    BLANK_ACCOUNT_PASSWORD(1001, MyExceptionConstants.BLANK_ACCOUNT_PASSWORD),
    /**
     * 用户名密码不能为空
     */
    UNKNOWN_ACCOUNT(1002, MyExceptionConstants.UNKNOWN_ACCOUNT),
    /**
     * 账号密码不匹配
     */
    PASSWORD_ERROR(1003, MyExceptionConstants.PASSWORD_ERROR),
    PERMISSION_DENIED(1004,"权限不足"),
    TOKEN_ERROR(1005,"token error"),
    PATH_NOT_FOUNT(1006,"路径不存在，请检查路径是否正确");



    private Integer code;

    private String msg;

    ExceptionEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return String.valueOf(code);
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
