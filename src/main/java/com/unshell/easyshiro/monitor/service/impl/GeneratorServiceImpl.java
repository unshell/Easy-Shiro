package com.unshell.easyshiro.monitor.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.Table;
import com.unshell.easyshiro.monitor.mapper.GeneratorMapper;
import com.unshell.easyshiro.monitor.service.IGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl implements IGeneratorService {
    private final GeneratorMapper generatorMapper;

    /**
     * 获取数据表分页信息
     *
     * @param request
     * @param tableName
     * @param schemaName
     * @return
     */
    @Override
    public IPage<Table> getTables(QueryRequest request, String tableName, String schemaName) {
        Page<Table> page = new Page<>(request.getPage(), request.getLimit());
        return generatorMapper.getTables(page, tableName, schemaName);
    }
}