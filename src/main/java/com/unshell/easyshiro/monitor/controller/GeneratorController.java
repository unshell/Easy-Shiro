package com.unshell.easyshiro.monitor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.unshell.easyshiro.common.controller.BaseController;
import com.unshell.easyshiro.common.entity.EasyResponse;
import com.unshell.easyshiro.common.entity.QueryRequest;
import com.unshell.easyshiro.monitor.entity.Table;
import com.unshell.easyshiro.monitor.helper.GeneratorHelper;
import com.unshell.easyshiro.monitor.service.IGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("generator")
@RequiredArgsConstructor
public class GeneratorController extends BaseController {
    private final IGeneratorService generatorService;
    private final GeneratorHelper generatorHelper;

    /**
     * 数据表分页数据
     *
     * @param request
     * @param tableName
     * @param datasource
     */
    @GetMapping("table/page")
    public EasyResponse queryTables(QueryRequest request, String tableName, String datasource) {
        IPage<Table> page = generatorService.getTables(request, tableName, generatorHelper.getSchemaName(datasource));
        return new EasyResponse().table(page);
    }

    /**
     * 代码生成
     *
     * @param module
     * @param datasource
     * @param tableNames
     */
    @PostMapping("generate")
    public EasyResponse generateTables(String module, String datasource, String tableNames) {
        generatorHelper.init()
                .setGlobalConfig()
                .setDataSourceConfig(datasource)
                .setPackageConfig(module)
//                .setTemplateConfig()
                .setStrategyConfig(tableNames)
                .execute();
        return new EasyResponse().success().message("代码已生成");
    }
}