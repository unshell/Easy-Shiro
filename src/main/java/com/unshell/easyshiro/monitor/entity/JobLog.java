package com.unshell.easyshiro.monitor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务调度日志
 */
@Data
@TableName("monitor_job_log")
public class JobLog implements Serializable {
    /**
     * 调度日志编号
     */
    @TableId(value = "LogId", type = IdType.AUTO)
    private Long logId;
    /**
     * 任务编号
     */
    @TableField("JobId")
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
     * 调度状态
     * {@link com.unshell.easyshiro.monitor.entity.enums.JobStatus}
     */
    @TableField("Status")
    private Integer status;
    /**
     * 异常信息
     */
    @TableField("Error")
    private String error;
    /**
     * 耗时
     */
    @TableField("Times")
    private Long times;
    /**
     * 创建时间
     */
    @TableField("CreateTime")
    private Date createTime;
}