package com.xh.service.impl;

import com.xh.BaseMockBeforeTests;
import com.xh.controller.LoginController;
import com.xh.entity.SysUser;
import com.xh.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest extends BaseMockBeforeTests {

    @Autowired
    private ISysUserService sysUserServiceImpl;

    @Test
    public void contextLoads() {
        SysUser user = new SysUser();
        user.setUsername("test3");
        user.setPassword("123456");
        user.setMobile("177556458741");
        user.setEmail("andy.@gmail.com");
        boolean flag = sysUserServiceImpl.addUser(user);
        Assert.assertTrue(flag);
    }

}
