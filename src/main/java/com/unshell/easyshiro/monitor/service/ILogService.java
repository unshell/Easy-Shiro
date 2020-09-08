package com.unshell.easyshiro.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.unshell.easyshiro.common.entity.EasyConstant;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import java.lang.reflect.Method;
import java.util.List;

public interface ILogService extends IService<Log> {
    /**
     * 查询监控日志分页信息
     *
     * @param request
     * @return
     */
    IPage<Log> queryLogPage(QueryRequest request, Log log);

    /**
     * 查询今日监控日志信息
     *
     * @param limit
     * @return
     */
    List<Log> queryLogListToday(Integer limit);

    /**
     * 保存监控日志(异步)
     *
     * @param point     切面
     * @param method    方法
     * @param ip        IP
     * @param operator  操作者
     * @param operation 操作内容
     * @param start     开始时间
     */
    @Async(EasyConstant.ASYNC_POOL)
    void saveLog(ProceedingJoinPoint point, Method method, String ip, String operator, String operation, boolean isPrivacy, long start);
}