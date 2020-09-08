package com.unshell.easyshiro.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.LoginLog;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ILoginLogService extends IService<LoginLog> {
    /**
     * 获取登录日志分页信息
     *
     * @param request  QueryRequest
     * @param loginLog 查询参数
     * @return IPage<LoginLog>
     */
    IPage<LoginLog> findLoginLogPage(QueryRequest request, LoginLog loginLog);

    /**
     * 获取登录日志
     *
     * @param loginLog 查询参数
     * @return List<LoginLog>
     */
    List<LoginLog> findLoginLog(LoginLog loginLog);

    /**
     * 保存登入日志
     *
     * @param loginLog
     */
    void saveLoginLogin(LoginLog loginLog, HttpServletRequest request);

    /**
     * 获取系统总访问次数
     *
     * @return
     */
    Long findTotalVisitCount();
}