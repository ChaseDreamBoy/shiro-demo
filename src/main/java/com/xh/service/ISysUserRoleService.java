package com.xh.service;

import com.xh.entity.SysRole;
import com.xh.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xh.vo.UserRoleVo;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * get roles by user id.
     *
     * @param userId user id.
     *
     * @return role list.
     */
    List<UserRoleVo> getRolesByUserId(Long userId);

    /**
     * del user-role by role id.
     *
     * @param roleId role id.
     *
     * @return is success.
     */
    boolean delByRoleId(long roleId);

}
