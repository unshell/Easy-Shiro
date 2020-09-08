package com.unshell.easyshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@MapperScan("com.unshell.easyshiro.*.mapper")
public class EasyShiroApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EasyShiroApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}