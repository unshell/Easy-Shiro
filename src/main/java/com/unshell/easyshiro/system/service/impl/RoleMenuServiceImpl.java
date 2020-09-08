package com.unshell.easyshiro.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unshell.easyshiro.system.entity.RoleMenu;
import com.unshell.easyshiro.system.entity.extend.RoleMenuTree;
import com.unshell.easyshiro.system.mapper.RoleMenuMapper;
import com.unshell.easyshiro.system.service.IRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
    /**
     * 查询角色权限数据
     *
     * @param roleId 角色编号
     * @return List<RoleMenuTree>
     */
    @Override
    public List<RoleMenuTree> queryPermissionsTreeByRoleId(int roleId) {
        return this.baseMapper.queryPermissionsTreeByRoleId(roleId);
    }

    /**
     * 删除角色权限
     *
     * @param roleId 角色编号
     */
    @Override
    @Transactional
    public void deleteRoleMenuByRoleId(Integer roleId) {
        this.baseMapper.deleteRoleMenuByRoleId(roleId);
    }

    /**
     * 更新角色权限
     *
     * @param roleId  角色编号
     * @param menuIds 菜单编号数组
     */
    @Override
    @Transactional
    public void insertRoleMenuByRoleId(Integer roleId, Integer[] menuIds) {
        this.deleteRoleMenuByRoleId(roleId);
        this.baseMapper.insertRoleMenuByRoleId(roleId, menuIds);
    }
}