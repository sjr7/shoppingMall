package com.suny.taotao.manager.sso;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * sso web 启动入口
 *
 * @author sunjianrong
 * @date 2019-02-22 上午 11:04
 */
@EnableAutoConfiguration
@EnableAsync
public class SsoWebApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SsoWebApplication.class).run(args);
    }
}
