package com.suny.taotao.manager.sso;


import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * sso web 启动入口
 *
 * @author sunjianrong
 * @date 2019-02-22 上午 11:04
 */
@SpringBootApplication
@EnableDubboConfig
@EnableAsync
public class SsoWebApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SsoWebApplication.class).run(args);
    }
}
