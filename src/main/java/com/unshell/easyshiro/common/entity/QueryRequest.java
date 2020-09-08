package com.unshell.easyshiro.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryRequest implements Serializable {
    /**
     * 当前页码
     */
    private int page;
    /**
     * 当前页面数据量
     */
    private int limit;

    public QueryRequest() {
        this.page = 1;
        this.limit = 10;
    }
}