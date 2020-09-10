package com.unshell.easyshiro.common.runner;

import com.unshell.easyshiro.common.service.RedisService;
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
    @Autowired
    private RedisService redisService;

    private final boolean AutoOpenBrowser = false;

    @Value("${server.port}")
    private String port;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            redisService.get("runner");
        } catch (Exception e) {
            log.info(" ___   ____  ___   _   __       ____   __    _   _    ");
            log.info("| |_) | |_  | | \\ | | ( (`     | |_   / /\\  | | | |   ");
            log.info("|_| \\ |_|__ |_|_/ |_| _)_)     |_|   /_/--\\ |_| |_|__ ");
            log.info("::Redis服务连接异常，程序自动断开::");
            context.close();
        }
        if (context.isActive()) {
            InetAddress address = InetAddress.getLocalHost();
            String url = "http://" + address.getHostAddress() + ":" + port + contextPath;
            log.info(" __    ___   _      ___   _      __   _____  ____ ");
            log.info("/ /`  / / \\ | |\\/| | |_) | |    / /\\   | |  | |_  ");
            log.info("\\_\\_, \\_\\_/ |_|  | |_|   |_|__ /_/--\\  |_|  |_|__ ");
            log.info("::Easy Shiro权限系统已启动，地址：{}::", url);
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