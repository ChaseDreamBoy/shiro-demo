package com.xh.service;

import com.xh.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色与菜单对应关系 服务类
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * del by role id.
     *
     * @param roleId role id.
     *
     * @return is success.
     */
    boolean delByRoleId(long roleId);

}
