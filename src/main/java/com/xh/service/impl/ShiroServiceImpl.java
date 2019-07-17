package com.xh.service.impl;

import com.xh.constant.SysConstants;
import com.xh.entity.SysMenu;
import com.xh.mapper.ShiroServiceMapper;
import com.xh.service.IShiroService;
import com.xh.service.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Service
public class ShiroServiceImpl implements IShiroService {

    private ShiroServiceMapper shiroServiceMapper;

    private ISysMenuService sysMenuServiceImpl;

    public ShiroServiceImpl(ShiroServiceMapper shiroServiceMapper,
                            ISysMenuService sysMenuServiceImpl) {
        this.shiroServiceMapper = shiroServiceMapper;
        this.sysMenuServiceImpl = sysMenuServiceImpl;
    }

    /**
     * get permission list by user id.
     *
     * @param userId user is
     *
     * @return permission list.
     */
    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;
        if (userId == SysConstants.ADMIN_ID) {
            // admin has all permission.
            List<SysMenu> menuList = sysMenuServiceImpl.list(null);
            permsList = new ArrayList<>(menuList.size());
            menuList.forEach(menu -> permsList.add(menu.getPerms()));
        } else {
            permsList = shiroServiceMapper.queryUserAllPermissions(userId);
        }
        // user permission list.
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    /**
     * check permission
     *
     * @param url resources url
     *
     * @return is auth.
     */
    @Override
    public boolean checkPermission(String url) {
        List<SysMenu> menus = this.sysMenuServiceImpl.getMenusByUrl(url);
        if (menus == null || menus.isEmpty() || menus.get(0) == null || StringUtils.isBlank(menus.get(0).getPerms())) {
            return true;
        }
        String[] permissions = menus.get(0).getPerms().split(",");
        Subject subject = SecurityUtils.getSubject();
        boolean[] flags = subject.isPermitted(permissions);
        if (flags == null || flags.length == 0) {
            return false;
        }
        for (boolean flag : flags) {
            if (flag) {
                return true;
            }
        }
        return false;
    }
}
