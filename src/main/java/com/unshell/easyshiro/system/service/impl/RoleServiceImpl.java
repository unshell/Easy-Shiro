package com.unshell.easyshiro.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.system.entity.Role;
import com.unshell.easyshiro.system.mapper.RoleMapper;
import com.unshell.easyshiro.system.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    /**
     * 查询角色分页数据
     *
     * @param request
     * @param role
     * @return IPage<Role>
     */
    @Override
    public IPage<Role> queryRolePage(QueryRequest request, Role role) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(role.getRoleName())) {
            wrapper.like(Role::getRoleName, role.getRoleName());
        }
        if (StringUtils.isNotBlank(role.getRoleCode())) {
            wrapper.like(Role::getRoleCode, role.getRoleCode());
        }
        if (StringUtils.isNotBlank(role.getRemark())) {
            wrapper.like(Role::getRemark, role.getRemark());
        }
        Page<Role> page = new Page<>(request.getPage(), request.getLimit());
        return this.page(page, wrapper);
    }

    /**
     * 查询角色数据
     *
     * @return Map
     */
    @Override
    public Map<Integer, String> queryRoleSelect() {
        return this.baseMapper.selectList(null)
                .stream()
                .collect(Collectors.toMap(Role::getRoleId, Role::getRoleName));
    }

    /**
     * 新增角色
     *
     * @param role
     */
    @Override
    @Transactional
    public void insertRole(Role role) {
        role.setCreateTime(new Date());
        role.setModifyTime(role.getCreateTime());
        this.save(role);
    }

    /**
     * 修改角色
     *
     * @param role
     */
    @Override
    @Transactional
    public void updateRole(Role role) {
        role.setModifyTime(new Date());
        this.baseMapper.updateById(role);
    }

    /**
     * 删除角色
     *
     * @param roleId 主键值
     */
    @Override
    @Transactional
    public void deleteRole(Integer roleId) {
        this.baseMapper.deleteById(roleId);
    }
}