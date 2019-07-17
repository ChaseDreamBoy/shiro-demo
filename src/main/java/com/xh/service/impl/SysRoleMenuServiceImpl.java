package com.xh.service.impl;

import com.xh.entity.SysRoleMenu;
import com.xh.mapper.SysRoleMenuMapper;
import com.xh.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    /**
     * del by role id.
     *
     * @param roleId role id.
     *
     * @return is success.
     */
    @Override
    public boolean delByRoleId(long roleId) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("role_id", roleId);
        return this.removeByMap(params);
    }
}
