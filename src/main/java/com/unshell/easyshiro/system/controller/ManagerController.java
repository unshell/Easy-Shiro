package com.unshell.easyshiro.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.unshell.easyshiro.common.annotation.ControllerEndpoint;
import com.unshell.easyshiro.common.controller.BaseController;
import com.unshell.easyshiro.common.entity.EasyResponse;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.system.entity.Manager;
import com.unshell.easyshiro.system.service.IManagerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/manager")
public class ManagerController extends BaseController {
    private final IManagerService managerService;

    /**
     * 系统用户分页信息
     *
     * @param request
     * @param manager
     */
    @GetMapping("page")
    @RequiresPermissions("manager:view")
    public EasyResponse managerPage(QueryRequest request, Manager manager) {
        IPage<Manager> page = managerService.queryManagerPage(request, manager);
        return new EasyResponse().success().table(page);
    }

    /**
     * 新增系统用户
     *
     * @param manager
     */
    @PostMapping("add")
    @RequiresPermissions("manager:add")
    @ControllerEndpoint(operation = "创建了系统用户", isPrivacy = true)
    public EasyResponse insertUser(Manager manager) {
        manager.setPassword(manager.getPhone());
        managerService.insertManager(manager);
        return new EasyResponse().success().message("用户创建成功");
    }

    /**
     * 更新系统用户
     *
     * @param manager
     */
    @PostMapping("update")
    @RequiresPermissions("manager:update")
    @ControllerEndpoint(operation = "更新了系统用户")
    public EasyResponse updateUser(Manager manager) {
        managerService.updateManager(manager);
        return new EasyResponse().success().message("用户更新成功");
    }

    /**
     * 删除系统用户
     *
     * @param id 主键
     */
    @PostMapping("delete")
    @RequiresPermissions("manager:delete")
    @ControllerEndpoint(operation = "删除了系统用户", message = "删除系统用户失败")
    public EasyResponse deleteUser(Integer id) {
        Manager current = super.getCurrentManager();
        Assert.isTrue(!current.getManagerId().equals(id), "本人不能删除自身账号");
        managerService.removeById(id);
        return new EasyResponse().success().message("用户已被移除");
    }

    /**
     * 更新系统用户状态
     *
     * @param managerId 主键
     * @param status    状态
     */
    @PostMapping("status")
    @RequiresPermissions("manager:update")
    public EasyResponse changeStatus(Integer managerId, Integer status) {
        managerService.updateById(new Manager(managerId, status));
        return new EasyResponse().success().message("用户状态已更新");
    }

    /**
     * 重置系统用户密码
     *
     * @param managerId 主键
     */
    @PostMapping("reset")
    @RequiresPermissions("manager:reset")
    @ControllerEndpoint(operation = "重置了系统用户密码")
    public EasyResponse resetPassword(Integer managerId) {
        managerService.resetPassword(managerId);
        return new EasyResponse().success().message("用户密码已重置");
    }

    /**
     * 更新系统用户密码
     *
     * @param loginName 登录账户
     * @param oldPsw    旧密码
     * @param newPsw    新密码
     */
    @PostMapping("password/{loginName}")
    @ControllerEndpoint(operation = "更新了系统用户密码", message = "更新密码异常")
    public EasyResponse changePassword(@NotBlank(message = "{required}") @PathVariable String loginName, String oldPsw, String newPsw) {
        Manager current = super.getCurrentManager();
        if (!StringUtils.equalsIgnoreCase(loginName, current.getLoginName())) {
            throw new RuntimeException("只有账号本人才能修改密码");
        }
        if (StringUtils.equalsIgnoreCase(oldPsw, newPsw)) {
            throw new RuntimeException("新旧密码不能相同");
        }
        managerService.changePassword(current.getManagerId(), oldPsw, newPsw);
        super.logout();
        return new EasyResponse().success().message("用户密码已更新");
    }

    /**
     * 更新系统用户头像
     *
     * @param name 头像
     */
    @PostMapping("avatar/{name}")
    public EasyResponse changeAvatar(@PathVariable String name) {
        Manager current = super.getCurrentManager();
        current.setAvatar(name);
        managerService.updateById(current);
        return new EasyResponse().success().message("头像已更新");
    }

    /**
     * 更新个人信息
     *
     * @param loginName 登录账户
     * @param manager
     */
    @PostMapping("info/{loginName}")
    public EasyResponse changeSelfInfo(@NotBlank(message = "{required}") @PathVariable String loginName, Manager manager) {
        Manager current = super.getCurrentManager();
        if (!StringUtils.equalsIgnoreCase(loginName, current.getLoginName())) {
            throw new RuntimeException("只有账号本人才能修改信息");
        }
        current.setEmail(manager.getEmail());
        current.setNickName(manager.getNickName());
        current.setAddress(manager.getAddress());
        current.setIntroduction(manager.getIntroduction());
        managerService.updateById(current);
        return new EasyResponse().success().message("个人信息已更新");
    }
}