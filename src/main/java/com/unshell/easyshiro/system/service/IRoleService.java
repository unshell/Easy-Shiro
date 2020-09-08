package com.unshell.easyshiro.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.system.entity.Role;

import java.util.Map;

public interface IRoleService extends IService<Role> {
    /**
     * 查询角色分页数据
     *
     * @param request
     * @param role
     * @return
     */
    IPage<Role> queryRolePage(QueryRequest request, Role role);

    /**
     * 查询角色数据
     *
     * @return
     */
    Map<Integer, String> queryRoleSelect();

    /**
     * 新增角色
     *
     * @param role
     */
    void insertRole(Role role);

    /**
     * 修改角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param roleId
     */
    void deleteRole(Integer roleId);
}