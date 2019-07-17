package com.xh.enums;

import com.xh.constant.ResultConstants;

/**
 * @author xiaohe
 * @version V1.0.0
 */
public enum SysResultEnums implements ResultEnum {

    /**
     * 20xx
     * 用户相关
     */
    ADD_USER_SUCCESS(ResultConstants.SUCCESS_CODE, "添加用户成功"),
    ADD_USER_FAIL(2001, "添加用户失败"),
    USER_LIST_SUCCESS(ResultConstants.SUCCESS_CODE, "获取用户列表成功"),
    /**
     * 21xx
     * company
     */
    COMPANY_LIST_SUCCESS(ResultConstants.SUCCESS_CODE, "获取公司列表成功"),
    COMPANY_ADD_SUCCESS(ResultConstants.SUCCESS_CODE, "添加公司成功"),
    COMPANY_DEL_SUCCESS(ResultConstants.SUCCESS_CODE, "删除公司成功"),
    COMPANY_DETAIL_SUCCESS(ResultConstants.SUCCESS_CODE, "查看公司详情成功"),
    COMPANY_UPDATE_SUCCESS(ResultConstants.SUCCESS_CODE, "更新公司成功"),
    /**
     * 22xx
     * user role
     */
    USER_ROLE_BY_ID(ResultConstants.SUCCESS_CODE, "根据 userId 获取角色成功"),
    USER_ROLE_DEL_SUCCESS(ResultConstants.SUCCESS_CODE, "解绑角色成功"),
    USER_ROLE_DEL_FAIL(2201, "解绑角色失败"),
    USER_ROLE_ADD_SUCCESS(ResultConstants.SUCCESS_CODE, "绑定角色成功"),
    USER_ROLE_ADD_FAIL(2202, "绑定角色失败"),
    /**
     * 23XX
     * role
     */
    ROLE_ADD_SUCCESS(ResultConstants.SUCCESS_CODE, "添加角色成功"),
    ROLE_ADD_FAIL(2301, "添加角色失败"),
    ROLE_DEL_SUCCESS(ResultConstants.SUCCESS_CODE, "删除角色成功"),
    ROLE_DEL_FAIL(2302, "删除角色失败"),
    ROLE_LIST_NOT_IN_USER(ResultConstants.SUCCESS_CODE, "获取该用户没有的权限成功");

    private Integer code;

    private String msg;

    SysResultEnums(Integer code, String msg) {
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
