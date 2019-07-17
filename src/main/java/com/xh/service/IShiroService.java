package com.xh.service;

import java.util.Set;

/**
 * @author xiaohe
 * @version V1.0.0
 */
public interface IShiroService {

    /**
     * get permission list by user id.
     *
     * @param userId user is
     *
     * @return permission list.
     */
    Set<String> getUserPermissions(long userId);

    /**
     * check permission
     *
     * @param url resources url
     *
     * @return is auth.
     */
    boolean checkPermission(String url);

}
