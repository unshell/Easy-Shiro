package com.unshell.easyshiro.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务
 */
@Data
@TableName("monitor_job")
public class Job implements Serializable {
    /**
     * 任务调度参数 key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    /**
     * 任务编号
     */
    @TableId(value = "JobId", type = IdType.AUTO)
    private Long jobId;
    /**
     * Bean名称
     */
    @TableField("BeanName")
    private String beanName;
    /**
     * 方法名称
     */
    @TableField("MethodName")
    private String methodName;
    /**
     * 参数
     */
    @TableField("Params")
    private String params;
    /**
     * Cron 表达式
     */
    @TableField("CronExpression")
    private String cronExpression;
    /**
     * 调度状态
     * {@link com.unshell.easyshiro.monitor.entity.enums.JobStatus}
     */
    @TableField("Status")
    private Integer status;
    /**
     * 备注
     */
    @TableField("Remark")
    private String remark;
    /**
     * 创建时间
     */
    @TableField("CreateTime")
    private Date createTime;
}