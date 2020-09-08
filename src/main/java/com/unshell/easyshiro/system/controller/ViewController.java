package com.unshell.easyshiro.system.controller;

import com.unshell.easyshiro.common.annotation.ControllerEndpoint;
import com.unshell.easyshiro.common.controller.BaseController;
import com.unshell.easyshiro.monitor.service.ILogService;
import com.unshell.easyshiro.system.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("systemViews")
@RequiredArgsConstructor
public class ViewController extends BaseController {
    private final IRoleService roleService;
    private final ILogService logService;

    /**
     * 首页视图
     */
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("User", super.getCurrentManager());
        return "index";
    }

    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    /**
     * 欢迎视图
     */
    @GetMapping("/welcome")
    public String welcome() {
        return "views/console/welcome";
    }

    @GetMapping("/workplace")
    public String workplace(Model model) {
        model.addAttribute("Manager", getCurrentManager());
        model.addAttribute("Logs", logService.queryLogListToday(20));
        return "views/console/workplace";
    }

    /**
     * 系统菜单视图
     */
    @GetMapping("/system/menu")
    public String systemMenu() {
        return "views/system/menu";
    }

    /**
     * 系统角色视图
     */
    @GetMapping("/system/role")
    public String systemRole() {
        return "views/system/role";
    }

    /**
     * 系统用户视图
     *
     * @param model
     */
    @GetMapping("/system/manager")
    public String systemManager(Model model) {
        model.addAttribute("RoleSelect", roleService.queryRoleSelect());
        return "views/system/manager";
    }

    /**
     * 系统字典视图
     */
    @GetMapping("/system/dictionary")
    public String systemDictionary() {
        return "views/system/dictionary";
    }

    /**
     * 登录视图
     */
    @GetMapping("/login")
    public String loginSystem() {
        return "views/template/login";
    }

    /**
     * 登出系统
     */
    @RequestMapping("/logout")
    @ControllerEndpoint(operation = "退出了系统")
    public String logoutSystem() {
        super.logout();
        return "redirect:/login";
    }

    /**
     * 系统用户个人信息视图
     */
    @GetMapping("/user/info")
    public String userInfo(Model model) {
        model.addAttribute("User", getCurrentManager());
        return "views/template/user-info";
    }

    /**
     * 用户头像视图
     */
    @GetMapping("/user/avatar")
    public String userAvatar(Model model) {
        model.addAttribute("User", getCurrentManager());
        return "views/template/user-avatar";
    }

    /**
     * 便签
     */
    @GetMapping("/tpl/note")
    public String tplNote() {
        return "views/tpl/tpl-note";
    }

    /**
     * 修改密码
     */
    @GetMapping("/tpl/password")
    public String tplPassword(Model model) {
        model.addAttribute("User", super.getCurrentManager());
        return "views/tpl/tpl-password";
    }

    /**
     * 系统主题
     */
    @GetMapping("/tpl/theme")
    public String tplTheme() {
        return "views/tpl/tpl-theme";
    }

    /**
     * Error 403
     */
    @GetMapping("/error/403")
    public String error403() {
        return "views/template/error-403";
    }

    /**
     * Error 404
     */
    @GetMapping("/error/404")
    public String error404() {
        return "views/template/error-404";
    }

    /**
     * Error 500
     */
    @GetMapping("/error/500")
    public String error500() {
        return "views/template/error-500";
    }
}