package com.unshell.easyshiro.common.entity;

import lombok.Data;

/**
 * 短信返回结果
 */
@Data
public class SmsResult {
    /**
     * 结果代码
     */
    private String code;
    /**
     * 结果说明
     */
    private String msg;
    /**
     * 短信编号
     */
    private String msgId;
    /**
     * 具体返回信息
     */
    private String extra;
}