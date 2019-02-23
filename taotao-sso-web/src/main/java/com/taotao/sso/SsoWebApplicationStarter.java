package com.taotao.sso;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * sso web 启动入口
 *
 * @author sunjianrong
 * @date 2019-02-22 上午 11:04
 */
@SpringBootApplication
public class SsoWebApplicationStarter extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SsoWebApplicationStarter.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SsoWebApplicationStarter.class, args);
    }


}
