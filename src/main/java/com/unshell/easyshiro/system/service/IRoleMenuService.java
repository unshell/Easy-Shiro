package com.unshell.easyshiro.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.unshell.easyshiro.system.entity.RoleMenu;
import com.unshell.easyshiro.system.entity.extend.RoleMenuTree;

import java.util.List;

public interface IRoleMenuService extends IService<RoleMenu> {
    /**
     * 查询角色权限数据
     *
     * @param roleId 角色编号
     * @return
     */
    List<RoleMenuTree> queryPermissionsTreeByRoleId(int roleId);

    /**
     * 删除角色权限
     *
     * @param roleId 角色编号
     */
    void deleteRoleMenuByRoleId(Integer roleId);

    /**
     * 更新角色权限
     *
     * @param roleId  角色编号
     * @param menuIds 菜单编号数组
     */
    void insertRoleMenuByRoleId(Integer roleId, Integer[] menuIds);
}