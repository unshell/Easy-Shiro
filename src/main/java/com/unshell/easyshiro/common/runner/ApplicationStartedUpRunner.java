package com.unshell.easyshiro.common.runner;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Slf4j
@Component
public class ApplicationStartedUpRunner implements ApplicationRunner {
    @Autowired
    private ConfigurableApplicationContext context;

    private final boolean AutoOpenBrowser = false;

    @Value("${server.port}")
    private String port;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (context.isActive()) {
            InetAddress address = InetAddress.getLocalHost();
            String url = "http://" + address.getHostAddress() + ":" + port + contextPath;
            if (AutoOpenBrowser && StringUtils.equalsIgnoreCase(active, "dev")) {
                String os = System.getProperty("os.name");
                // 开发模式下且环境为Windows下打开默认浏览器
                if (StringUtils.containsIgnoreCase(os, "windows")) {
                    Runtime.getRuntime().exec("cmd  /c  start " + url);
                }
            }
        }
    }
}