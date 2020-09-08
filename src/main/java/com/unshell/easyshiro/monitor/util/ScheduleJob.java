package com.unshell.easyshiro.monitor.util;

import com.unshell.easyshiro.common.utils.SpringContextUtil;
import com.unshell.easyshiro.monitor.entity.Job;
import com.unshell.easyshiro.monitor.entity.JobLog;
import com.unshell.easyshiro.monitor.entity.enums.JobStatus;
import com.unshell.easyshiro.monitor.service.IJobLogService;
import lombok.extern.slf4j.Slf4j;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.Future;

@Slf4j
public class ScheduleJob extends QuartzJobBean {

    private ThreadPoolTaskExecutor scheduleJobExecutorService = SpringContextUtil.getBean("scheduleJobExecutorService", ThreadPoolTaskExecutor.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Job scheduleJob = (Job) context.getMergedJobDataMap().get(Job.JOB_PARAM_KEY);

        IJobLogService jobLogService = SpringContextUtil.getBean(IJobLogService.class);

        JobLog jobLog = new JobLog();
        jobLog.setJobId(scheduleJob.getJobId());
        jobLog.setBeanName(scheduleJob.getBeanName());
        jobLog.setMethodName(scheduleJob.getMethodName());
        jobLog.setParams(scheduleJob.getParams());
        jobLog.setCreateTime(new Date());

        long startTime = System.currentTimeMillis();

        try {
            log.info("任务准备执行,任务Id:{}", scheduleJob.getJobId());
            ScheduleRunnable runnable = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = scheduleJobExecutorService.submit(runnable);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes(times);
            jobLog.setStatus(JobStatus.SUCCESS.ordinal());
            log.info("任务执行完毕，任务Id：{} 总共耗时：{} 毫秒", scheduleJob.getJobId(), times);
        } catch (Exception e) {
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes(times);
            jobLog.setStatus(JobStatus.FAIL.ordinal());
            jobLog.setError(e.getMessage());
            log.error("任务执行失败，任务Id：" + scheduleJob.getJobId(), e);
        } finally {
            jobLogService.saveJobLog(jobLog);
        }
    }
}