package com.xh.filter;

import com.xh.shiro.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Slf4j
public class CustomShiroFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        log.info("custom filter createToken ...");
        return new UsernamePasswordToken();
    }

    /**
     * Processes requests where the subject was denied access as determined by the
     * {@link #isAccessAllowed(ServletRequest, ServletResponse, Object) isAccessAllowed}
     * method.
     *
     * @param request  the incoming <code>ServletRequest</code>
     * @param response the outgoing <code>ServletResponse</code>
     *
     * @return <code>true</code> if the request should continue to be processed; false if the subclass will
     * handle/render the response directly.
     *
     * @throws Exception if there is an error processing the request.
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("custom filter onAccessDenied ...");

        return true;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.info("onPreHandle ============== ");
        Subject subject = SecurityUtils.getSubject();
        ShiroUtil.reloadAuthorizing(subject.getPrincipal());
        subject.isPermitted("light:wield");
        subject.isAuthenticated();
        subject.hasRole("admin");
        // executeLogin(request, response);
        return isAccessAllowed(request, response, mappedValue) || this.onAccessDenied(request, response, mappedValue);
    }

}
