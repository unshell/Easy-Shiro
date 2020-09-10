package com.unshell.easyshiro.common.service;

import com.unshell.easyshiro.common.entity.SmsResult;
import com.unshell.easyshiro.common.properties.EasyProperties;
import com.unshell.easyshiro.common.properties.SmsProperties;
import com.unshell.easyshiro.common.utils.EasyUtil;
import com.unshell.easyshiro.system.service.IDictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 短信服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SmsService {
    private final EasyProperties easyProperties;
    private final IDictionaryService dictionaryService;
    private Map<String, String> configure;

    @Value("${spring.profiles.active}")
    private String active;

    /**
     * 发送短信
     */
    public void send(String phone, String template) {
        this.send(phone, template, new Object[0]);
    }

    /**
     * 发送短信
     *
     * @param phone    收信方手机号
     * @param template 短信模板
     * @param params   模板参数
     */
    public void send(String phone, String template, Object... params) {
        SmsProperties properties = easyProperties.getSms();
        this.configure = dictionaryService.queryDictionaryMap("sms");
        Assert.notNull(configure, "未找到模板配置");
        if (!EasyUtil.match("[1]\\d{10}", phone)) {
            throw new RuntimeException("收信方手机号格式不正确");
        }
        String text = this.configure.get(template);
        Assert.notNull(text, "未找到对应模板");
        if (params.length > 0) {
            text = String.format(text, params);
        }
        properties.setText(text);
        // 真实环境下调用短信接口
        if (StringUtils.equalsIgnoreCase(active, "master")) {
            RestTemplate rest = new RestTemplate();
            SmsResult smsResult = rest.postForObject(properties.getUrl(), configure, SmsResult.class);
            log.info(smsResult.toString());
        }
        // TODO：发送后的操作
    }
}