package com.unshell.easyshiro.common.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class EasyResponse extends HashMap<String, Object> {
    public EasyResponse code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public EasyResponse message(String message) {
        this.put("msg", message);
        return this;
    }

    public EasyResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    /**
     * 表格数据的响应格式(Layui 默认字段)
     *
     * @param page 分页数据
     * @return
     */
    public EasyResponse table(IPage<?> page) {
        this.put("code", 0);
        this.put("data", page.getRecords());
        this.put("count", page.getTotal());
        return this;
    }

    public EasyResponse success() {
        this.code(HttpStatus.OK);
        return this;
    }

    public EasyResponse fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public EasyResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}