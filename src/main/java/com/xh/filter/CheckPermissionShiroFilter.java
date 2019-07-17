package com.xh.filter;

import com.xh.enums.ExceptionEnums;
import com.xh.service.IShiroService;
import com.xh.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Slf4j
public class CheckPermissionShiroFilter extends AuthorizationFilter {

    private IShiroService shiroServiceImpl;

    public CheckPermissionShiroFilter(IShiroService shiroServiceImpl) {
        this.shiroServiceImpl = shiroServiceImpl;
    }

    /**
     * Returns <code>true</code> if the request is allowed to proceed through the filter normally, or <code>false</code>
     * if the request should be handled by the
     * {@link #onAccessDenied(ServletRequest, ServletResponse, Object) onAccessDenied(request,response,mappedValue)}
     * method instead.
     *
     * @param request     the incoming <code>ServletRequest</code>
     * @param response    the outgoing <code>ServletResponse</code>
     * @param mappedValue the filter-specific config value mapped to this filter in the URL rules mappings.
     *
     * @return <code>true</code> if the request should proceed through the filter normally, <code>false</code> if the
     * request should be processed by this filter's
     * {@link #onAccessDenied(ServletRequest, ServletResponse, Object)} method instead.
     *
     * @throws Exception if an error occurs during processing.
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        // ShiroHttpServletRequest
        ShiroHttpServletRequest request1 = (ShiroHttpServletRequest) request;
        String requestUrl = request1.getRequestURI();
        boolean flag = shiroServiceImpl.checkPermission(requestUrl);
        if (flag) {
            return true;
        }

//        Subject subject = SecurityUtils.getSubject();
//        ShiroUtil.reloadAuthorizing(subject.getPrincipal());

        ServletUtils.printResultJsonToPage(response, ExceptionEnums.PERMISSION_DENIED);
        return false;
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
     * @throws IOException if there is an error processing the request.
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        return true;
    }

    /**
     * Returns <code>true</code> if
     * {@link #isAccessAllowed(ServletRequest, ServletResponse, Object) isAccessAllowed(Request,Response,Object)},
     * otherwise returns the result of
     * {@link #onAccessDenied(ServletRequest, ServletResponse, Object) onAccessDenied(Request,Response,Object)}.
     *
     * @return <code>true</code> if
     * {@link #isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, Object) isAccessAllowed},
     * otherwise returns the result of
     * {@link #onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse) onAccessDenied}.
     *
     * @throws Exception if an error occurs.
     */
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return isAccessAllowed(request, response, mappedValue);
    }


}
