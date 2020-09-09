package com.unshell.easyshiro.monitor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.unshell.easyshiro.common.annotation.ControllerEndpoint;
import com.unshell.easyshiro.common.annotation.Limit;
import com.unshell.easyshiro.common.controller.BaseController;
import com.unshell.easyshiro.common.entity.EasyResponse;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.common.service.ValidateCodeService;
import com.unshell.easyshiro.common.utils.EasyUtil;
import com.unshell.easyshiro.monitor.entity.LoginLog;
import com.unshell.easyshiro.monitor.service.ILoginLogService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class LoginController extends BaseController {
    private final ILoginLogService loginLogService;
    private final ValidateCodeService validateCodeService;

    /**
     * 登录日志分页数据
     *
     * @param request
     * @param loginLog
     */
    @GetMapping("/login/log/page")
    public EasyResponse loginLogList(QueryRequest request, LoginLog loginLog) {
        IPage<LoginLog> page = loginLogService.findLoginLogPage(request, loginLog);
        return new EasyResponse().table(page);
    }

    /**
     * 管理员登录
     *
     * @param loginName  账号
     * @param password   密码
     * @param verifyCode 验证码
     * @param rememberMe 记住我
     * @param request
     */
    @PostMapping("/login")
    @ControllerEndpoint(operation = "登录了系统", isPrivacy = true, message = "登录异常")
    @Limit(name = "登录接口", period = 60, count = 10, key = "login", prefix = "limit")
    public EasyResponse login(@NotBlank(message = "账号不能为空") String loginName,
                              @NotBlank(message = "密码不能为空") String password,
                              @NotBlank(message = "验证码不能为空") String verifyCode,
                              boolean rememberMe, HttpServletRequest request) {
        if (!EasyUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException("非Ajax请求");
        }
        HttpSession session = request.getSession();
        validateCodeService.check(session.getId(), verifyCode);
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password, rememberMe);
        super.login(token);
        // 保存登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginName(loginName);
        loginLog.setSystemBrowserInfo();
        this.loginLogService.saveLoginLogin(loginLog, request);
        return new EasyResponse().success();
    }

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/images/captcha")
    @Limit(name = "验证码接口", period = 60, count = 10, key = "captcha", prefix = "limit")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        validateCodeService.create(request, response);
    }
}