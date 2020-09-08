package com.unshell.easyshiro.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色&权限
 */
@Data
@TableName("system_role_menu")
public class RoleMenu implements Serializable {
    /**
     * 角色编号
     */
    @TableField("RoleId")
    private Integer roleId;
    /**
     * 权限编号
     */
    @TableField("MenuId")
    private Integer menuId;
}