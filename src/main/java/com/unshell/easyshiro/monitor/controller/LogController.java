package com.unshell.easyshiro.monitor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.unshell.easyshiro.common.controller.BaseController;
import com.unshell.easyshiro.common.entity.EasyResponse;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.Log;
import com.unshell.easyshiro.monitor.service.ILogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
@RequiredArgsConstructor
public class LogController extends BaseController {
    private final ILogService logService;

    /**
     * 监控日志分页数据
     *
     * @param request
     * @param log
     */
    @GetMapping("page")
    public EasyResponse logPage(QueryRequest request, Log log) {
        IPage<Log> page = logService.queryLogPage(request, log);
        return new EasyResponse().table(page);
    }
}