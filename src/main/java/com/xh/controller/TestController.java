package com.xh.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private SessionDAO sessionDAO;

    @GetMapping("/1")
    public Object test1() {
        return "ok1";
    }

    /**
     * 需要登录认证，不能rememberme
     *
     * @return string
     */
    @RequiresAuthentication
    @GetMapping("/2")
    public Object test2() {
        Subject subject = SecurityUtils.getSubject();
        List list = subject.getPreviousPrincipals().asList();
        return "ok2";
    }

    @RequiresAuthentication
    @RequiresPermissions("admin:test3")
    @GetMapping("/3")
    public Object test3() {
        return "ok3";
    }

    @RequiresRoles("admin")
    @GetMapping("/4")
    public Object test4() {
        return "ok4";
    }

    @RequiresRoles("admin")
    @GetMapping("/5")
    public Object test5() {
        return "ok5";
    }

    @RequiresRoles("vip")
    @GetMapping("/6")
    public Object test6() {
        return "ok6";
    }

    @GetMapping("/7")
    public Object test7() {
        return "ok7";
    }

    @RequiresPermissions("admin:test3")
    @GetMapping("/8")
    public Object test8() {
        return "ok8";
    }

    @GetMapping("/9")
    public Object test9() {
        return "ok9";
    }

}
