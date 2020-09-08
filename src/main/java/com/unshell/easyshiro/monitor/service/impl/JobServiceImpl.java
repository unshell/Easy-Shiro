package com.unshell.easyshiro.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.Job;
import com.unshell.easyshiro.monitor.entity.enums.ScheduleStatus;
import com.unshell.easyshiro.monitor.mapper.JobMapper;
import com.unshell.easyshiro.monitor.service.IJobService;
import com.unshell.easyshiro.monitor.util.ScheduleUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {
    private final Scheduler scheduler;

    /**
     * 服务器加载 SERVLET 时候运行一次
     */
    @PostConstruct
    public void init() {
        List<Job> scheduleJobs = this.baseMapper.selectList(null);
        scheduleJobs.forEach(job -> {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, job.getJobId());
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, job);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, job);
            }
        });
    }

    /**
     * 获取定时任务分页数据
     *
     * @param request
     * @param job
     * @return
     */
    @Override
    public IPage<Job> findJobPage(QueryRequest request, Job job) {
        LambdaQueryWrapper<Job> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(job.getBeanName())) {
            wrapper.eq(Job::getBeanName, job.getBeanName());
        }
        if (StringUtils.isNotBlank(job.getMethodName())) {
            wrapper.eq(Job::getMethodName, job.getMethodName());
        }
        Page<Job> page = new Page<>(request.getPage(), request.getLimit());
        wrapper.orderByDesc(Job::getCreateTime);
        return this.page(page, wrapper);
    }

    /**
     * 获取定时任务
     *
     * @param jobId 任务id
     * @return 定时任务
     */
    @Override
    public Job findJob(Long jobId) {
        return this.getById(jobId);
    }

    /**
     * 创建定时任务
     *
     * @param job
     */
    @Override
    @Transactional
    public void insertJob(Job job) {
        job.setCreateTime(new Date());
        job.setStatus(ScheduleStatus.PAUSE.ordinal());
        this.save(job);
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    /**
     * 更新定时任务
     *
     * @param job
     */
    @Override
    @Transactional
    public void updateJob(Job job) {
        ScheduleUtils.updateScheduleJob(scheduler, job);
        this.baseMapper.updateById(job);
    }

    /**
     * 更新定时任务状态
     *
     * @param jobId
     * @param status
     */
    @Override
    @Transactional
    public void updateBath(String jobId, Integer status) {
        Job job = new Job();
        job.setJobId(Long.valueOf(jobId));
        job.setStatus(status);
        this.baseMapper.updateById(job);
    }

    /**
     * 运行定时任务
     *
     * @param jobId 定时任务id
     */
    @Override
    public void run(String jobId) {
        ScheduleUtils.run(scheduler, this.findJob(Long.valueOf(jobId)));
    }

    /**
     * 暂停定时任务
     *
     * @param jobId 定时任务id
     */
    @Override
    public void pause(String jobId) {
        ScheduleUtils.pauseJob(scheduler, Long.valueOf(jobId));
        this.updateBath(jobId, ScheduleStatus.PAUSE.ordinal());
    }

    /**
     * 恢复定时任务
     *
     * @param jobId 定时任务id
     */
    @Override
    public void resume(String jobId) {
        ScheduleUtils.resumeJob(scheduler, Long.valueOf(jobId));
        this.updateBath(jobId, ScheduleStatus.NORMAL.ordinal());
    }
}