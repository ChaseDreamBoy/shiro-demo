package com.xh.mapper;

import com.xh.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * get role list. the role list not in user's role.
     *
     * @param userId user id.
     *
     * @return role list.
     */
    List<SysRole> getRolesNotInUser(@Param("userId") long userId);

}
