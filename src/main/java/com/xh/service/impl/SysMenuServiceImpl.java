package com.xh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xh.entity.SysMenu;
import com.xh.mapper.SysMenuMapper;
import com.xh.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author xiaohe
 * @since 2019-07-15
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    /**
     * get menus by url.
     *
     * @param url resources url.
     *
     * @return menus list.
     */
    @Override
    public List<SysMenu> getMenusByUrl(String url) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", url);
        return this.list(queryWrapper);
    }
}
