package com.unshell.easyshiro.monitor.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.unshell.easyshiro.monitor.entity.Table;
import org.apache.ibatis.annotations.Param;

public interface GeneratorMapper {
    <T> IPage<Table> getTables(Page<T> page, @Param("tableName") String tableName, @Param("schemaName") String schemaName);
}