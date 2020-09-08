package com.unshell.easyshiro.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.Job;

public interface IJobService extends IService<Job> {
    /**
     * 获取定时任务分页数据
     *
     * @param request
     * @param job
     * @return
     */
    IPage<Job> findJobPage(QueryRequest request, Job job);

    /**
     * 获取定时任务
     *
     * @param jobId 任务id
     * @return 定时任务
     */
    Job findJob(Long jobId);

    /**
     * 创建定时任务
     *
     * @param job
     */
    void insertJob(Job job);

    /**
     * 更新定时任务
     *
     * @param job
     */
    void updateJob(Job job);

    /**
     * 更新定时任务
     *
     * @param jobId
     * @param status
     */
    void updateBath(String jobId, Integer status);

    /**
     * 运行定时任务
     *
     * @param jobId 定时任务id
     */
    void run(String jobId);

    /**
     * 暂停定时任务
     *
     * @param jobId 定时任务id
     */
    void pause(String jobId);

    /**
     * 恢复定时任务
     *
     * @param jobId 定时任务id
     */
    void resume(String jobId);
}