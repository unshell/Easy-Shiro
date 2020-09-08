package com.unshell.easyshiro.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:easy.properties"})
@ConfigurationProperties(prefix = "easy")
public class EasyProperties {
    private ShiroProperties shiro = new ShiroProperties();
    private SmsProperties sms = new SmsProperties();
    private ValidateCodeProperties validate = new ValidateCodeProperties();
}