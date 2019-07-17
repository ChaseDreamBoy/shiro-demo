package com.xh.controller;


import com.xh.entity.SysRole;
import com.xh.entity.SysUserRole;
import com.xh.enums.SysResultEnums;
import com.xh.service.ISysUserRoleService;
import com.xh.service.ISysUserService;
import com.xh.shiro.AccountProfile;
import com.xh.shiro.ShiroUtil;
import com.xh.util.Result;
import com.xh.vo.UserRoleVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 前端控制器
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/sys-user-role")
public class SysUserRoleController {

    private ISysUserRoleService sysUserRoleServiceImpl;

    private ISysUserService sysUserServiceImpl;

    public SysUserRoleController(ISysUserRoleService sysUserRoleServiceImpl,
                                 ISysUserService sysUserServiceImpl) {
        this.sysUserRoleServiceImpl = sysUserRoleServiceImpl;
        this.sysUserServiceImpl = sysUserServiceImpl;
    }

    @GetMapping("/userRoles")
    public Result getUserRole(Long userId) {
        // 用户的 角色
        List<UserRoleVo> roles = this.sysUserRoleServiceImpl.getRolesByUserId(userId);
        return Result.customResultEnum(SysResultEnums.USER_ROLE_BY_ID, roles);
    }

    @PostMapping("delUserRole")
    public Result delUserRole(Long userRoleId, Long userId) {
        boolean flag = this.sysUserRoleServiceImpl.removeById(userRoleId);
        if (flag) {
            AccountProfile profile = sysUserServiceImpl.getAccountProfile(userId);
            ShiroUtil.reloadAuthorizing(profile);
            return Result.customResultEnum(SysResultEnums.USER_ROLE_DEL_SUCCESS);
        }
        return Result.customResultEnum(SysResultEnums.USER_ROLE_DEL_FAIL);
    }

    @PostMapping("addUserRole")
    public Result addUserRole(SysUserRole sysUserRole) {
        boolean flag = this.sysUserRoleServiceImpl.save(sysUserRole);
        if (flag) {
            AccountProfile profile = sysUserServiceImpl.getAccountProfile(sysUserRole.getUserId());
            ShiroUtil.reloadAuthorizing(profile);
            return Result.customResultEnum(SysResultEnums.USER_ROLE_ADD_SUCCESS);
        }
        return Result.customResultEnum(SysResultEnums.USER_ROLE_ADD_FAIL);
    }

}
