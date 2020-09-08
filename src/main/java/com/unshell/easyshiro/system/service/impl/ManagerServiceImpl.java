package com.unshell.easyshiro.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.common.utils.Md5Util;
import com.unshell.easyshiro.system.entity.Manager;
import com.unshell.easyshiro.system.entity.enums.ManagerStatus;
import com.unshell.easyshiro.system.mapper.ManagerMapper;
import com.unshell.easyshiro.system.service.IManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {
    /**
     * 查询系统用户分页信息
     *
     * @param request 分页条件
     * @param manager 筛选条件
     * @return IPage<Manager>
     */
    @Override
    public IPage<Manager> queryManagerPage(QueryRequest request, Manager manager) {
        LambdaQueryWrapper<Manager> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(manager.getLoginName())) {
            wrapper.like(Manager::getLoginName, manager.getLoginName());
        }
        if (StringUtils.isNotBlank(manager.getPhone())) {
            wrapper.like(Manager::getPhone, manager.getPhone());
        }
        wrapper.orderByDesc(Manager::getCreateTime);
        Page<Manager> page = new Page<>(request.getPage(), request.getLimit());
        return this.page(page, wrapper);
    }

    /**
     * 根据账号查询系统用户信息
     *
     * @param loginName 账号
     * @return
     */
    @Override
    public Manager queryManagerByLoginName(String loginName) {
        LambdaQueryWrapper<Manager> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Manager::getLoginName, loginName);
        wrapper.last("LIMIT 1");
        return this.baseMapper.selectOne(wrapper);
    }

    /**
     * 新增系统用户
     *
     * @param manager
     */
    @Override
    @Transactional
    public void insertManager(Manager manager) {
        manager.setSignature(Md5Util.signature());
        manager.setStatus(ManagerStatus.ENABLE.ordinal());
        manager.setCreateTime(new Date());
        manager.setPassword(Md5Util.encrypt(manager.getSignature(), manager.getPassword()));
        manager.setModifyTime(manager.getCreateTime());
        this.save(manager);
    }

    /**
     * 更新系统用户
     *
     * @param manager
     */
    @Override
    @Transactional
    public void updateManager(Manager manager) {
        manager.setLoginName(null);
        manager.setPassword(null);
        manager.setSignature(null);
        this.baseMapper.updateById(manager);
    }

    /**
     * 重置系统用户密码
     *
     * @param id 主键
     */
    @Override
    @Transactional
    public void resetPassword(Integer id) {
        Manager manager = this.baseMapper.selectById(id);
        Assert.notNull(manager, "系统用户信息异常");
        manager.setSignature(Md5Util.signature());
        manager.setPassword(Md5Util.encrypt(manager.getSignature(), Manager.DEFAULT_PASSWORD));
        this.baseMapper.updateById(manager);
    }

    /**
     * 修改系统用户密码
     *
     * @param id
     * @param oldPsw
     * @param newPsw
     */
    @Transactional
    public void changePassword(Integer id, String oldPsw, String newPsw) {
        Manager manager = this.baseMapper.selectById(id);
        if (!StringUtils.equalsIgnoreCase(manager.getPassword(), Md5Util.encrypt(manager.getSignature(), oldPsw))) {
            throw new RuntimeException("旧密码不正确");
        }
        Assert.notNull(manager, "系统用户信息异常");
        manager.setSignature(Md5Util.signature());
        manager.setPassword(Md5Util.encrypt(manager.getSignature(), newPsw));
        this.baseMapper.updateById(manager);
    }
}