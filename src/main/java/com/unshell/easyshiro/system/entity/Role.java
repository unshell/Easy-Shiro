package com.unshell.easyshiro.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 */
@Data
@TableName("system_role")
public class Role implements Serializable {
    /**
     * 角色编号
     */
    @TableId(value = "RoleId", type = IdType.AUTO)
    private Integer roleId;
    /**
     * 角色名称
     */
    @TableField("RoleName")
    private String roleName;
    /**
     * 角色代码
     */
    @TableField("RoleCode")
    private String roleCode;
    /**
     * 描述
     */
    @TableField("Remark")
    private String remark;
    /**
     * 修改时间
     */
    @TableField("ModifyTime")
    private Date modifyTime;
    /**
     * 创建时间
     */
    @TableField("CreateTime")
    private Date createTime;
}