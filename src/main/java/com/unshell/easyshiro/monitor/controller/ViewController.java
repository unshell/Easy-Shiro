package com.unshell.easyshiro.monitor.controller;

import com.unshell.easyshiro.common.controller.BaseController;
import com.unshell.easyshiro.monitor.helper.GeneratorHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("monitorViews")
@RequiredArgsConstructor
public class ViewController extends BaseController {
    private final GeneratorHelper generatorHelper;

    /**
     * 操作日志视图
     */
    @GetMapping("/monitor/log")
    public String logView() {
        return "views/monitor/log";
    }

    /**
     * 登录日志视图
     */
    @GetMapping("/monitor/login/log")
    public String loginLogView() {
        return "views/monitor/login_log";
    }

    /**
     * 任务调度视图
     */
    @GetMapping("/monitor/job")
    public String jobView() {
        return "views/monitor/job";
    }

    /**
     * 任务调度日志视图
     */
    @GetMapping("/monitor/job/log")
    public String jobLogView() {
        return "views/monitor/job_log";
    }

    /**
     * 代码生成视图
     */
    @GetMapping("/monitor/generate")
    public String generateView(Model model) {
        List<String> datasource = generatorHelper.getDataSource();
        model.addAttribute("DataSource", datasource);
        return "views/monitor/generate";
    }
}