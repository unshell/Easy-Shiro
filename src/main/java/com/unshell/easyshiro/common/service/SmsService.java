package com.unshell.easyshiro.common.service;

import com.unshell.easyshiro.common.entity.SmsResult;
import com.unshell.easyshiro.system.service.IDictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 短信服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SmsService {
    private final IDictionaryService dictionaryService;

    public void send() {
        Map<String, String> configure = dictionaryService.queryDictionaryMap("sms");
        RestTemplate rest = new RestTemplate();
        SmsResult smsResult = rest.postForObject(configure.get("url"), configure, SmsResult.class);
        // TODO 发送后的操作
        log.info(smsResult.toString());
    }
}