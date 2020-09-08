package com.unshell.easyshiro.monitor.entity;

import lombok.Data;

/**
 * 数据表
 */
@Data
public class Table {
    /**
     * 名称
     */
    private String name;
    /**
     * 数据库名称
     */
    private String schemaName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据行数
     */
    private Long dataRows;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 创建时间
     */
    private String createTime;
}