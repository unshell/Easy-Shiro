package com.unshell.easyshiro.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.unshell.easyshiro.common.annotation.ControllerEndpoint;
import com.unshell.easyshiro.common.entity.EasyResponse;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.system.entity.Role;
import com.unshell.easyshiro.system.entity.extend.RoleMenuTree;
import com.unshell.easyshiro.system.service.IRoleMenuService;
import com.unshell.easyshiro.system.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;
    private final IRoleMenuService roleMenuService;

    /**
     * 角色分页数据
     *
     * @param request
     * @param role
     */
    @GetMapping("page")
    @RequiresPermissions("role:view")
    public EasyResponse rolePage(QueryRequest request, Role role) {
        IPage<Role> page = roleService.queryRolePage(request, role);
        return new EasyResponse().table(page);
    }

    /**
     * 创建角色
     *
     * @param role
     */
    @PostMapping("add")
    @RequiresPermissions("role:add")
    @ControllerEndpoint(operation = "创建了角色")
    public EasyResponse addRole(Role role) {
        roleService.insertRole(role);
        return new EasyResponse().success().message("角色创建成功");
    }

    /**
     * 更新角色
     *
     * @param role
     */
    @PostMapping("update")
    @RequiresPermissions("role:update")
    @ControllerEndpoint(operation = "更新了角色")
    public EasyResponse updateRole(Role role) {
        roleService.updateRole(role);
        return new EasyResponse().success().message("角色更新成功");
    }

    /**
     * 删除角色
     *
     * @param id
     */
    @PostMapping("delete")
    @RequiresPermissions("role:delete")
    @ControllerEndpoint(operation = "删除了角色")
    public EasyResponse deleteRole(Integer id) {
        roleService.deleteRole(id);
        return new EasyResponse().success().message("角色删除成功");
    }

    /**
     * 角色关联的权限列表
     *
     * @param id
     */
    @GetMapping("menu/tree")
    public EasyResponse roleMenuTree(Integer id) {
        List<RoleMenuTree> trees = roleMenuService.queryPermissionsTreeByRoleId(id);
        return new EasyResponse().success().data(trees);
    }

    /**
     * 更新角色关联的权限
     *
     * @param roleId
     * @param menuIds
     */
    @PostMapping("menu/add")
    @RequiresPermissions("role:perms")
    @ControllerEndpoint(operation = "更新了角色权限")
    public EasyResponse addRoleMenu(Integer roleId, String menuIds) {
        String[] var1 = menuIds.split(",");
        Integer[] var2 = new Integer[var1.length];
        for (int i = 0; i < var1.length; i++) {
            var2[i] = NumberUtils.toInt(var1[i], 0);
        }
        roleMenuService.insertRoleMenuByRoleId(roleId, var2);
        return new EasyResponse().success().message("角色权限已更新");
    }
}