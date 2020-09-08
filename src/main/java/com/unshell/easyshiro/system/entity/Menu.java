package com.unshell.easyshiro.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单
 */
@Data
@TableName("system_menu")
public class Menu implements Serializable {
    /**
     * 根编号
     */
    public static final int TOP_NODE_ID = -1;
    /**
     * 菜单编号
     */
    @TableId("MenuId")
    private Integer menuId;
    /**
     * 父级编号
     */
    @TableField("ParentId")
    private Integer parentId;
    /**
     * 名称
     */
    @TableField("Name")
    private String name;
    /**
     * URL
     */
    @TableField("Url")
    private String url;
    /**
     * 图标
     */
    @TableField("Icon")
    private String icon;
    /**
     * 权限标识
     */
    @TableField("Perms")
    private String perms;
    /**
     * 排序
     */
    @TableField("Sort")
    private Integer sort;
    /**
     * 菜单类型
     * {@link com.unshell.easyshiro.system.entity.enums.MenuType}
     */
    @TableField("Type")
    private Integer type;
    /**
     * 创建时间
     */
    @TableField("CreateTime")
    private Date createTime;
    /**
     * 默认展开状态
     */
    @TableField(exist = false)
    private Boolean open;
    /**
     * 是否禁用
     */
    @TableField(exist = false)
    private Boolean disabled;

    public Menu() {
        this.open = true;
    }
}