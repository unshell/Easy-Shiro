package com.unshell.easyshiro.monitor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.unshell.easyshiro.common.annotation.ControllerEndpoint;
import com.unshell.easyshiro.common.controller.BaseController;
import com.unshell.easyshiro.common.entity.EasyResponse;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.Job;
import com.unshell.easyshiro.monitor.service.IJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController extends BaseController {
    private final IJobService jobService;

    /**
     * 任务分页数据
     *
     * @param request
     * @param job
     */
    @GetMapping("page")
    public EasyResponse jobPage(QueryRequest request, Job job) {
        IPage<Job> page = jobService.findJobPage(request, job);
        return new EasyResponse().table(page);
    }

    /**
     * 添加任务
     *
     * @param job
     */
    @PostMapping("add")
    @ControllerEndpoint(operation = "创建了定时任务")
    public EasyResponse addJob(Job job) {
        jobService.insertJob(job);
        return new EasyResponse().success().message("任务创建成功");
    }

    /**
     * 更新任务
     *
     * @param job
     */
    @PostMapping("update")
    @ControllerEndpoint(operation = "更新了定时任务")
    public EasyResponse updateJob(Job job) {
        jobService.updateJob(job);
        return new EasyResponse().success().message("任务更新成功");
    }

    /**
     * 执行任务
     *
     * @param jobId
     */
    @GetMapping("run/{jobId}")
    @ControllerEndpoint(operation = "执行了定时任务")
    public EasyResponse runJob(@NotBlank(message = "任务编号不能为空") @PathVariable String jobId) {
        jobService.run(jobId);
        return new EasyResponse().success().message("任务已运行");
    }

    /**
     * 暂停任务
     *
     * @param jobId
     */
    @GetMapping("pause/{jobId}")
    @ControllerEndpoint(operation = "停止了定时任务")
    public EasyResponse pauseJob(@NotBlank(message = "任务编号不能为空") @PathVariable String jobId) {
        jobService.pause(jobId);
        return new EasyResponse().success().message("任务已暂停");
    }

    /**
     * 恢复任务
     *
     * @param jobId
     */
    @GetMapping("resume/{jobId}")
    @ControllerEndpoint(operation = "恢复了定时任务")
    public EasyResponse resumeJob(@NotBlank(message = "任务编号不能为空") @PathVariable String jobId) {
        jobService.resume(jobId);
        return new EasyResponse().success().message("任务已恢复");
    }
}