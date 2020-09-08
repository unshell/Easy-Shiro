package com.unshell.easyshiro.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.Table;

public interface IGeneratorService {
    /**
     * 获取数据表分页信息
     *
     * @param request
     * @param tableName
     * @param schemaName
     * @return
     */
    IPage<Table> getTables(QueryRequest request, String tableName, String schemaName);
}