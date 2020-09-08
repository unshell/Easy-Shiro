package com.unshell.easyshiro.monitor.helper;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GeneratorHelper {
    private final DynamicDataSourceProperties properties;
    private AutoGenerator generator;

    /**
     * 代码生成器
     */
    public GeneratorHelper init() {
        this.generator = new AutoGenerator();
        return this;
    }

    /**
     * 全局设置
     */
    public GeneratorHelper setGlobalConfig() {
        GlobalConfig config = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        config.setOutputDir(projectPath + "/src/main/java");
        config.setAuthor("generator");
        config.setOpen(false);
        this.generator.setGlobalConfig(config);
        return this;
    }

    /**
     * 数据源设置
     *
     * @param datasource
     */
    public GeneratorHelper setDataSourceConfig(final String datasource) {
        DataSourceConfig config = new DataSourceConfig();
        DataSourceProperty property = properties.getDatasource().get(datasource);
        config.setUrl(property.getUrl());
        config.setDriverName(property.getDriverClassName());
        config.setUsername(property.getUsername());
        config.setPassword(property.getPassword());
        this.generator.setDataSource(config);
        return this;
    }

    /**
     * 包设置
     *
     * @param module 模块名
     */
    public GeneratorHelper setPackageConfig(final String module) {
        PackageConfig config = new PackageConfig();
        config.setModuleName(module);
        config.setParent("com.unshell.easyshiro");
        this.generator.setPackageInfo(config);
        return this;
    }

    /**
     * 配置模板
     */
    public GeneratorHelper setTemplateConfig() {
        TemplateConfig config = new TemplateConfig();
        config.setEntity("/generator/entity.java");
        config.setMapper("/generator/mapper.java");
        config.setService("/generator/service.java");
        config.setController("/generator/controller.java");
        config.setServiceImpl("/generator/serviceImpl.java");
        config.setXml(null);
        this.generator.setTemplate(config);
        return this;
    }

    /**
     * 策略配置
     *
     * @param tableNames 数据表名(操作多个时,用逗号隔开)
     */
    public GeneratorHelper setStrategyConfig(final String tableNames) {
        StrategyConfig config = new StrategyConfig();
        config.setNaming(NamingStrategy.underline_to_camel);
        config.setColumnNaming(NamingStrategy.underline_to_camel);
        config.setEntityLombokModel(true);
        config.setRestControllerStyle(true);
        // 写于父类中的公共字段
        config.setSuperEntityColumns("id");
        config.setInclude(tableNames.split(","));
        config.setControllerMappingHyphenStyle(true);
        config.setTablePrefix(generator.getPackageInfo().getModuleName() + "_");
        this.generator.setStrategy(config);
        return this;
    }

    /**
     * 执行
     */
    public void execute() {
        long time = System.currentTimeMillis();
        // 默认使用Freemarker模板引擎
        this.generator.setTemplateEngine(new FreemarkerTemplateEngine());
        this.generator.execute();
        log.info("生成完毕，耗时 " + (System.currentTimeMillis() - time) + " ms");
    }

    /**
     * 获取SchemaName
     *
     * @return String
     */
    public String getSchemaName(String datasource) {
        if (StringUtils.isBlank(datasource)) return "";
        return StringUtils.substringBefore(StringUtils.substringAfterLast(this.properties.getDatasource().get(datasource).getUrl(), StringPool.SLASH), StringPool.QUESTION_MARK);
    }

    /**
     * 获取有效的数据库
     *
     * @return List
     */
    public List<String> getDataSource() {
        return this.properties.getDatasource().keySet().stream().collect(Collectors.toList());
    }
}