package com.xh.service;

import com.xh.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * get menus by url.
     *
     * @param url resources url.
     *
     * @return menus list.
     */
    List<SysMenu> getMenusByUrl(String url);

}
