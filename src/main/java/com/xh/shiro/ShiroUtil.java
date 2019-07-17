package com.xh.shiro;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * @author xiaohe
 * @version V1.0.0
 */
public class ShiroUtil {

    public static AccountProfile convertPrincipal(Object principal) {
        try {
            if (principal instanceof AccountProfile) {
                return (AccountProfile) principal;
            } else {
                return JSON.parseObject(JSON.toJSON(principal).toString(), AccountProfile.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static AccountProfile getCurUser() {
        return convertPrincipal(SecurityUtils.getSubject().getPrincipal());
    }

    public static void reloadAuthorizing(Object principal) {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyRealm myShiroRealm = (MyRealm) rsm.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, realmName);
        subject.runAs(principals);
        if (myShiroRealm.isAuthenticationCachingEnabled()) {
            myShiroRealm.getAuthenticationCache().remove(principals);
        }
        if (myShiroRealm.isAuthorizationCachingEnabled()) {
            // 删除指定用户shiro权限
            myShiroRealm.getAuthorizationCache().remove(principals);
        }
        // 刷新权限
        subject.releaseRunAs();
    }

}
