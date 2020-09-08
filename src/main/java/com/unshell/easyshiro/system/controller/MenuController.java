package com.unshell.easyshiro.system.controller;

import com.unshell.easyshiro.common.annotation.ControllerEndpoint;
import com.unshell.easyshiro.common.controller.BaseController;
import com.unshell.easyshiro.common.entity.EasyResponse;
import com.unshell.easyshiro.system.entity.Manager;
import com.unshell.easyshiro.system.entity.Menu;
import com.unshell.easyshiro.system.entity.extend.MenuTree;
import com.unshell.easyshiro.system.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("menu")
public class MenuController extends BaseController {
    private final IMenuService menuService;

    /**
     * 菜单列表数据
     *
     * @return
     */
    @GetMapping("list")
    @RequiresPermissions("menu:view")
    public EasyResponse menuList() {
        List<Menu> menus = menuService.queryMenuList(null);
        return new EasyResponse().put("code", 0).data(menus);
    }

    /**
     * 获取系统用户菜单
     *
     * @param loginName 登录名
     * @return
     */
    @GetMapping("{loginName}")
    public EasyResponse managerMenus(@NotBlank(message = "{required}") @PathVariable String loginName) {
        Manager current = super.getCurrentManager();
        if (!StringUtils.equalsIgnoreCase(loginName, current.getLoginName())) {
            throw new RuntimeException("您无权获取别人的菜单");
        }
        List<MenuTree> trees = menuService.queryManagerMenuTrees(loginName);
        return new EasyResponse().success().data(MenuTree.getTree(trees));
    }

    /**
     * 创建菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("add")
    @RequiresPermissions("menu:add")
    @ControllerEndpoint(operation = "创建了菜单")
    public EasyResponse addMenu(Menu menu) {
        menuService.insertMenu(menu);
        return new EasyResponse().success().message("菜单创建成功");
    }

    /**
     * 更新菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("update")
    @RequiresPermissions("menu:update")
    @ControllerEndpoint(operation = "更新了菜单")
    public EasyResponse updateMenu(Menu menu) {
        menuService.updateMenu(menu);
        return new EasyResponse().success().message("菜单更新成功");
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @PostMapping("delete")
    @RequiresPermissions("menu:delete")
    @ControllerEndpoint(operation = "删除了菜单")
    public EasyResponse deleteMenu(@RequestParam(value = "id[]") Set<Integer> id) {
        menuService.deleteMenu(id);
        return new EasyResponse().success().message("菜单删除成功");
    }
}