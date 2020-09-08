package com.unshell.easyshiro.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.unshell.easyshiro.system.entity.Menu;
import com.unshell.easyshiro.system.entity.enums.MenuType;
import com.unshell.easyshiro.system.entity.extend.MenuTree;

import java.util.List;
import java.util.Set;

public interface IMenuService extends IService<Menu> {
    /**
     * 查询菜单列表
     *
     * @param type
     * @return
     */
    List<Menu> queryMenuList(MenuType type);

    /**
     * 查询菜单树状结构列表
     *
     * @param loginName
     * @return
     */
    List<MenuTree> queryManagerMenuTrees(String loginName);

    /**
     * 查询用户权限集合
     *
     * @param loginName
     * @return
     */
    List<String> queryManagerPermissions(String loginName);

    /**
     * 创建菜单
     *
     * @param menu
     */
    void insertMenu(Menu menu);

    /**
     * 更新菜单
     *
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * 删除菜单
     *
     * @param id 主键
     */
    void deleteMenu(Set<Integer> id);
}