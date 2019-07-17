package com.xh.controller;


import com.xh.entity.SysRole;
import com.xh.enums.SysResultEnums;
import com.xh.service.ISysRoleService;
import com.xh.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

    private ISysRoleService sysRoleServiceImpl;

    public SysRoleController(ISysRoleService sysRoleServiceImpl) {
        this.sysRoleServiceImpl = sysRoleServiceImpl;
    }

    @GetMapping("roleNotInUser")
    public Result getRolesNotInUser(Long userId) {
        List<SysRole> roles = this.sysRoleServiceImpl.getRolesNotInUser(userId);
        return Result.customResultEnum(SysResultEnums.ROLE_LIST_NOT_IN_USER, roles);
    }

    @PostMapping("addRole")
    public Result addRole(SysRole sysRole) {
        boolean flag = this.sysRoleServiceImpl.addRole(sysRole);
        if (flag) {
            return Result.customResultEnum(SysResultEnums.ROLE_ADD_SUCCESS);
        }
        return Result.customResultEnum(SysResultEnums.ROLE_ADD_FAIL);
    }

    @PostMapping("delRole")
    public Result delRole(Long roleId) {
        boolean flag = this.sysRoleServiceImpl.delRole(roleId);
        if (flag) {
            return Result.customResultEnum(SysResultEnums.ROLE_DEL_SUCCESS);
        }
        return Result.customResultEnum(SysResultEnums.ROLE_DEL_FAIL);
    }

}
