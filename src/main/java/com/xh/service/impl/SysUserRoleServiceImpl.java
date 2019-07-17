package com.xh.service.impl;

import com.xh.entity.SysRole;
import com.xh.entity.SysUserRole;
import com.xh.mapper.SysUserRoleMapper;
import com.xh.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xh.vo.UserRoleVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    private SysUserRoleMapper sysUserRoleMapper;

    public SysUserRoleServiceImpl(SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    /**
     * get roles by user id.
     *
     * @param userId user id.
     *
     * @return role list.
     */
    @Override
    public List<UserRoleVo> getRolesByUserId(Long userId) {
        if (userId == null || userId < 1) {
            return new ArrayList<>();
        }
        return this.sysUserRoleMapper.getRolesByUserId(userId);
    }

    /**
     * del user-role by role id.
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
