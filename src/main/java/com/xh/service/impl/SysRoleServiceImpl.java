package com.xh.service.impl;

import com.xh.entity.SysRole;
import com.xh.mapper.SysRoleMapper;
import com.xh.service.ISysRoleMenuService;
import com.xh.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xh.service.ISysUserRoleService;
import com.xh.shiro.ShiroUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    private ISysRoleMenuService sysRoleMenuServiceImpl;

    private ISysUserRoleService sysUserRoleService;

    private SysRoleMapper sysRoleMapper;

    public SysRoleServiceImpl(ISysRoleMenuService sysRoleMenuServiceImpl,
                              ISysUserRoleService sysUserRoleService,
                              SysRoleMapper sysRoleMapper) {
        this.sysRoleMenuServiceImpl = sysRoleMenuServiceImpl;
        this.sysUserRoleService = sysUserRoleService;
        this.sysRoleMapper = sysRoleMapper;
    }

    /**
     * role list not in user.
     *
     * @param userId user id.
     *
     * @return role list.
     */
    @Override
    public List<SysRole> getRolesNotInUser(long userId) {
        return this.sysRoleMapper.getRolesNotInUser(userId);
    }

    /**
     * add role.
     *
     * @param role role info.
     *
     * @return is success.
     */
    @Override
    public boolean addRole(SysRole role) {
        role.setCreateTime(LocalDateTime.now());
        role.setCreateUserId(ShiroUtil.getCurUser() == null ? null : ShiroUtil.getCurUser().getUserId());
        return save(role);
    }

    /**
     * adl role
     *
     * @param roleId role id
     *
     * @return is success
     */
    @Override
    public boolean delRole(long roleId) {
        this.sysUserRoleService.delByRoleId(roleId);
        this.sysRoleMenuServiceImpl.delByRoleId(roleId);
        return removeById(roleId);
    }
}
