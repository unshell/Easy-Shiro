package com.unshell.easyshiro.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.system.entity.Manager;

public interface IManagerService extends IService<Manager> {
    /**
     * 查询系统用户分页信息
     *
     * @param request 分页条件
     * @param manager 筛选条件
     * @return IPage<Manager>
     */
    IPage<Manager> queryManagerPage(QueryRequest request, Manager manager);

    /**
     * 根据账号查询系统用户信息
     *
     * @param loginName 账号
     * @return Manager
     */
    Manager queryManagerByLoginName(String loginName);

    /**
     * 新增系统用户
     *
     * @param manager
     */
    void insertManager(Manager manager);

    /**
     * 更新系统用户
     *
     * @param manager
     */
    void updateManager(Manager manager);

    /**
     * 重置系统用户密码
     *
     * @param id 主键
     */
    void resetPassword(Integer id);

    /**
     * 修改系统用户密码
     *
     * @param id     主键
     * @param oldPsw 旧密码
     * @param newPsw 新密码
     */
    void changePassword(Integer id, String oldPsw, String newPsw);
}