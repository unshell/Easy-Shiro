package com.unshell.easyshiro.monitor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.unshell.easyshiro.common.entity.EasyResponse;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.JobLog;
import com.unshell.easyshiro.monitor.service.IJobLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job/log")
public class JobLogController {
    private final IJobLogService jobLogService;

    /**
     * 任务日志分页数据
     *
     * @param request
     * @param jobLog
     * @return
     */
    @GetMapping("page")
    public EasyResponse jobLogPage(QueryRequest request, JobLog jobLog) {
        IPage<JobLog> page = jobLogService.findJobLogPage(request, jobLog);
        return new EasyResponse().table(page);
    }
}