package com.unshell.easyshiro.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.unshell.easyshiro.system.entity.Menu;
import com.unshell.easyshiro.system.entity.extend.MenuTree;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> queryManagerPermissions(String loginName);

    List<MenuTree> queryManagerMenuTrees(String loginName);
}