package com.unshell.easyshiro.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 监控日志
 */
@Data
@TableName("monitor_log")
public class Log {
    /**
     * 编号
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Long id;
    /**
     * 操作者
     */
    @TableField("Operator")
    private String operator;
    /**
     * 操作内容
     */
    @TableField("Operation")
    private String operation;
    /**
     * 操作方法
     */
    @TableField("Method")
    private String method;
    /**
     * 操作方法参数
     */
    @TableField("Params")
    private String params;
    /**
     * 运行时间
     */
    @TableField("Time")
    private Long time;
    /**
     * IP地址
     */
    @TableField("IpAddress")
    private String ipAddress;
    /**
     * 创建时间
     */
    @TableField("CreateTime")
    private Date createTime;

    private transient String startDate;
    private transient String endDate;
}