package com.unshell.easyshiro.common.properties;

import lombok.Data;

@Data
public class SmsProperties {
    private String url;
    private String account;
    private String extend;
    private String mobile;
    private String sign;
    private String text;
}