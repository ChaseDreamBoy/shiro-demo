package com.xh.service;

import com.xh.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xh.shiro.AccountProfile;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * get users by user name.
     *
     * @param userName user name.
     *
     * @return users
     */
    List<SysUser> getUsersByName(String userName);

    /**
     * add user.
     *
     * @param user user info
     *
     * @return is success.
     */
    boolean addUser(SysUser user);

    /**
     * get principal
     *
     * @param userId user id
     *
     * @return AccountProfile -> principal
     */
    AccountProfile getAccountProfile(long userId);

}
