package com.unshell.easyshiro.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.common.utils.IpUtil;
import com.unshell.easyshiro.monitor.entity.LoginLog;
import com.unshell.easyshiro.monitor.mapper.LoginLogMapper;
import com.unshell.easyshiro.monitor.service.ILoginLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {
    /**
     * 获取登录日志分页信息
     *
     * @param request  QueryRequest
     * @param loginLog 查询参数
     * @return IPage<LoginLog>
     */
    @Override
    public IPage<LoginLog> findLoginLogPage(QueryRequest request, LoginLog loginLog) {
        LambdaQueryWrapper<LoginLog> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(loginLog.getLoginName())) {
            wrapper.like(LoginLog::getLoginName, loginLog.getLoginName());
        }
        if (StringUtils.isNotBlank(loginLog.getStartDate()) && StringUtils.isNotBlank(loginLog.getEndDate())) {
            wrapper.ge(LoginLog::getLoginName, loginLog.getStartDate()).le(LoginLog::getLoginTime, loginLog.getEndDate());
        }
        wrapper.orderByDesc(LoginLog::getLoginTime);
        Page<LoginLog> page = new Page<>(request.getPage(), request.getLimit());
        return this.page(page, wrapper);
    }

    /**
     * 获取登录日志
     *
     * @param loginLog 查询参数
     * @return List<LoginLog>
     */
    @Override
    public List<LoginLog> findLoginLog(LoginLog loginLog) {
        return null;
    }

    /**
     * 保存登入日志
     *
     * @param loginLog
     */
    @Override
    @Transactional
    public void saveLoginLogin(LoginLog loginLog, HttpServletRequest request) {
        loginLog.setLoginTime(new Date());
        String ip = IpUtil.getIpAddress(request);
        loginLog.setIpAddress(ip);
        loginLog.setSystemBrowserInfo();
        this.save(loginLog);
    }

    /**
     * 获取系统总访问次数
     *
     * @return
     */
    @Override
    public Long findTotalVisitCount() {
        return null;
    }
}