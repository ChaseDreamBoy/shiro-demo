package com.xh.mapper;

import com.xh.entity.SysRole;
import com.xh.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xh.vo.UserRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * get roles by user id.
     *
     * @param userId user id.
     *
     * @return role list.
     */
    List<UserRoleVo> getRolesByUserId(@Param("userId") Long userId);

}
