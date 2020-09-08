package com.unshell.easyshiro.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 */
@Data
@TableName("system_manager")
public class Manager implements Serializable {
    /**
     * 默认头像
     */
    public static final String DEFAULT_AVATAR = "head.jpg";
    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";
    /**
     * 用户编号
     */
    @TableId(value = "ManagerId", type = IdType.AUTO)
    private Integer managerId;
    /**
     * 登录账号
     */
    @TableField("LoginName")
    private String loginName;
    /**
     * 登录密码
     */
    @JsonIgnore
    @TableField("Password")
    private String password;
    /**
     * 昵称
     */
    @TableField("NickName")
    private String nickName;
    /**
     * 头像
     */
    @TableField("Avatar")
    private String avatar;
    /**
     * 手机号
     */
    @TableField("Phone")
    private String phone;
    /**
     * 邮箱
     */
    @TableField("Email")
    private String email;
    /**
     * 地址
     */
    @TableField("Address")
    private String address;
    /**
     * 用户状态
     * {@link com.unshell.easyshiro.system.entity.enums.ManagerStatus}
     */
    @TableField("Status")
    private Integer status;
    /**
     * 加密签名
     */
    @JsonIgnore
    @TableField("Signature")
    private String signature;
    /**
     * 角色编号
     */
    @TableField("RoleId")
    private Integer roleId;
    /**
     * 个人介绍
     */
    @TableField("Introduction")
    private String introduction;
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

    public Manager() {
    }

    public Manager(Integer _managerId, Integer _status) {
        this.managerId = _managerId;
        this.status = _status;
    }
}