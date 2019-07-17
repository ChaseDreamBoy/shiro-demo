package com.xh.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xh.shiro.AccountProfile;
import com.xh.shiro.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Slf4j
@Controller
public class LoginController {

    @PostMapping("/submit")
    public Object submit(String username, String password) {

        log.info("username-->{}, password-->{}", username, password);

        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(username);
        token.setPassword(password.toCharArray());
        // token.setRememberMe(true)
        SecurityUtils.getSubject().login(token);


        return "redirect:/index";
    }

    @GetMapping("/index")
    public Object index(HttpServletRequest request) throws IOException {

        Object principal = SecurityUtils.getSubject().getPrincipal();

        AccountProfile user = ShiroUtil.convertPrincipal(principal);

        if (user != null) {
            request.setAttribute("username", user.getUsername());
        }

        log.info("role : [{}]", SecurityUtils.getSubject().hasRole("admin"));

        return "index.html";
    }

}
