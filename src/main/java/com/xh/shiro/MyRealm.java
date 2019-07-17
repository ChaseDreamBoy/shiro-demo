package com.xh.shiro;

import com.xh.constant.MyExceptionConstants;
import com.xh.entity.SysUser;
import com.xh.service.IShiroService;
import com.xh.service.ISysUserService;
import com.xh.util.MyEncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Slf4j
@Component
public class MyRealm extends AuthorizingRealm {

    private ISysUserService sysUserServiceImpl;

    private IShiroService shiroServiceImpl;

    public MyRealm(ISysUserService sysUserServiceImpl, IShiroService shiroServiceImpl) {
        this.sysUserServiceImpl = sysUserServiceImpl;
        this.shiroServiceImpl = shiroServiceImpl;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principals == null || principals.isEmpty() || principals.getPrimaryPrincipal() == null) {
            return info;
        }
        Object principal = principals.getPrimaryPrincipal();
        AccountProfile user = ShiroUtil.convertPrincipal(principal);
        if (user == null) {
            return info;
        }
        Set<String> permissionSet = this.shiroServiceImpl.getUserPermissions(user.getUserId());
        if (permissionSet == null || permissionSet.isEmpty()) {
            return info;
        }
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 认证
     *
     * @param token
     *
     * @return
     *
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        // 数据库匹配，认证
        // 如果不一致就抛出异常（shiro内部异常等）
        String username = usernamePasswordToken.getUsername();
        String password = Arrays.toString(usernamePasswordToken.getPassword());
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new UnknownAccountException(MyExceptionConstants.BLANK_ACCOUNT_PASSWORD);
        }
        List<SysUser> sysUsers = this.sysUserServiceImpl.getUsersByName(username);
        // UnknownAccountException - 错误的帐号
        if (sysUsers == null || sysUsers.size() != 1 || sysUsers.get(0) == null ||
                StringUtils.isBlank(sysUsers.get(0).getPassword())) {
            throw new UnknownAccountException(MyExceptionConstants.UNKNOWN_ACCOUNT);
        }
        SysUser sysUser = sysUsers.get(0);
        Object salt = sysUser.getSalt();
        // 123456 =
        String hashedPasswordBase64 = MyEncryptUtils.encrypt(password, String.valueOf(salt));
        if (!sysUser.getPassword().equals(hashedPasswordBase64)) {
            log.info(hashedPasswordBase64);
            log.info(sysUser.getPassword());
            // IncorrectCredentialsException -- 错误的凭证
            throw new IncorrectCredentialsException(MyExceptionConstants.PASSWORD_ERROR);
        }
        AccountProfile profile = AccountProfile.createAccountProfile(sysUser);
        return new SimpleAuthenticationInfo(profile, token.getCredentials(), getName());
    }

}
