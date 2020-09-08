package com.unshell.easyshiro.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unshell.easyshiro.system.entity.RoleMenu;
import com.unshell.easyshiro.system.entity.extend.RoleMenuTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    List<RoleMenuTree> queryPermissionsTreeByRoleId(int roleId);

    int insertRoleMenuByRoleId(@Param("roleId") Integer roleId, @Param("menuIds") Integer[] menuIds);

    int deleteRoleMenuByRoleId(Integer roleId);
}