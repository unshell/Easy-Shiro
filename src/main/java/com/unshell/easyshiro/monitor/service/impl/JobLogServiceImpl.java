package com.unshell.easyshiro.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.JobLog;
import com.unshell.easyshiro.monitor.mapper.JobLogMapper;
import com.unshell.easyshiro.monitor.service.IJobLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobLogServiceImpl extends ServiceImpl<JobLogMapper, JobLog> implements IJobLogService {

    /**
     * 获取任务调度日志分页数据
     *
     * @param request
     * @param jobLog
     * @return
     */
    @Override
    public IPage<JobLog> findJobLogPage(QueryRequest request, JobLog jobLog) {
        QueryWrapper<JobLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(JobLog::getCreateTime);
        Page<JobLog> page = new Page<>(request.getPage(), request.getLimit());
        return this.page(page, queryWrapper);
    }

    /**
     * 保存定时任务日志
     *
     * @param log
     */
    @Override
    @Transactional
    public void saveJobLog(JobLog log) {
        this.save(log);
    }
}