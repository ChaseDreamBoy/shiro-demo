package com.xh.controller;


import com.xh.entity.SysUser;
import com.xh.enums.SysResultEnums;
import com.xh.service.ISysUserService;
import com.xh.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    private ISysUserService sysUserServiceImpl;

    public SysUserController(ISysUserService sysUserServiceImpl) {
        this.sysUserServiceImpl = sysUserServiceImpl;
    }

    @PostMapping("add")
    public Result addUser(SysUser user) {
        boolean flag = this.sysUserServiceImpl.addUser(user);
        if (flag) {
            return Result.customResultEnum(SysResultEnums.ADD_USER_SUCCESS);
        }
        return Result.customResultEnum(SysResultEnums.ADD_USER_FAIL);
    }

    @GetMapping("list")
    public Result getAllUser(){
        List<SysUser> users = this.sysUserServiceImpl.list(null);
        return Result.customResultEnum(SysResultEnums.USER_LIST_SUCCESS, users);
    }

}
