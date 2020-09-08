package com.unshell.easyshiro.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.JobLog;

public interface IJobLogService extends IService<JobLog> {
    /**
     * 获取任务调度日志分页数据
     *
     * @param request
     * @param jobLog
     * @return
     */
    IPage<JobLog> findJobLogPage(QueryRequest request, JobLog jobLog);

    /**
     * 保存定时任务日志
     *
     * @param log
     */
    void saveJobLog(JobLog log);
}