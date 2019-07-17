package com.xh.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaohe
 * @version V1.0.0
 */
public interface ShiroServiceMapper {
    /**
     * query user all permission.
     *
     * @param userId user id
     *
     * @return permission list.
     */
    List<String> queryUserAllPermissions(@Param("userId") long userId);
}
