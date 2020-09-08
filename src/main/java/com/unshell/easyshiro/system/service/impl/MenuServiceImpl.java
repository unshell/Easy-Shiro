package com.unshell.easyshiro.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.unshell.easyshiro.common.authentication.ShiroRealm;
import com.unshell.easyshiro.system.entity.Menu;
import com.unshell.easyshiro.system.entity.enums.MenuType;
import com.unshell.easyshiro.system.entity.extend.MenuTree;
import com.unshell.easyshiro.system.mapper.MenuMapper;
import com.unshell.easyshiro.system.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    private final ShiroRealm shiroRealm;

    /**
     * 查询菜单列表
     *
     * @param type 菜单类型
     * @return List<Menu>
     */
    @Override
    public List<Menu> queryMenuList(MenuType type) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        if (type != null) {
            wrapper.eq(Menu::getType, type);
        }
        wrapper.orderByAsc(Menu::getMenuId).orderByAsc(Menu::getSort);
        return this.baseMapper.selectList(wrapper).stream()
                .filter(Objects::nonNull)
                .map(p -> {
                    if (p.getType().equals(MenuType.BUTTON.ordinal()))
                        p.setDisabled(true);
                    else
                        p.setDisabled(false);
                    return p;
                }).collect(Collectors.toList());
    }

    /**
     * 查询菜单树状结构列表
     *
     * @return List<MenuTree>
     */
    @Override
    public List<MenuTree> queryManagerMenuTrees(String loginName) {
        return this.baseMapper.queryManagerMenuTrees(loginName);
    }

    /**
     * 查询用户权限集合
     *
     * @param loginName 账号
     * @return List<String>
     */
    @Override
    public List<String> queryManagerPermissions(String loginName) {
        return this.baseMapper.queryManagerPermissions(loginName);
    }

    /**
     * 新增菜单
     *
     * @param menu
     */
    @Override
    @Transactional
    public void insertMenu(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(Menu.TOP_NODE_ID);
        }
        Menu mode = this.baseMapper.selectById(menu.getMenuId());
        Assert.isNull(mode, "编号已经被使用");
        menu.setCreateTime(new Date());
        this.baseMapper.insert(menu);
    }

    /**
     * 修改菜单
     *
     * @param menu
     */
    @Override
    @Transactional
    public void updateMenu(Menu menu) {
        this.baseMapper.updateById(menu);
        shiroRealm.clearCache();
    }

    /**
     * 删除菜单
     *
     * @param id 编号集合
     */
    @Override
    @Transactional
    public void deleteMenu(Set<Integer> id) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(Menu::getMenuId, id);
        this.baseMapper.delete(queryWrapper);
        shiroRealm.clearCache();
    }
}