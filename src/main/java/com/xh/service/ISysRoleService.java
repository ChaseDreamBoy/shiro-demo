package com.xh.service;

import com.xh.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * role list not in user.
     *
     * @param userId user id.
     *
     * @return role list.
     */
    List<SysRole> getRolesNotInUser(long userId);

    /**
     * add role.
     *
     * @param role role info.
     *
     * @return is success.
     */
    boolean addRole(SysRole role);

    /**
     * adl role
     *
     * @param roleId role id
     *
     * @return is success
     */
    boolean delRole(long roleId);

}
