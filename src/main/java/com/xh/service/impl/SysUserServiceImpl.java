package com.xh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xh.entity.SysUser;
import com.xh.mapper.SysUserMapper;
import com.xh.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xh.shiro.AccountProfile;
import com.xh.shiro.ShiroUtil;
import com.xh.util.MyEncryptUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    /**
     * get users by user name.
     *
     * @param userName user name.
     *
     * @return users
     */
    @Override
    public List<SysUser> getUsersByName(String userName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        return this.sysUserMapper.selectList(queryWrapper);
    }

    /**
     * add user.
     *
     * @param user user info
     *
     * @return is success.
     */
    @Override
    public boolean addUser(SysUser user) {
        String salt = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        String password = MyEncryptUtils.encrypt(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(password);
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        user.setCreateUserId(ShiroUtil.getCurUser() == null ? null : ShiroUtil.getCurUser().getUserId());
        return this.save(user);
    }

    /**
     * get principal
     *
     * @param userId user id
     *
     * @return AccountProfile -> principal
     */
    @Override
    public AccountProfile getAccountProfile(long userId) {
        return AccountProfile.createAccountProfile(this.getById(userId));
    }
}
