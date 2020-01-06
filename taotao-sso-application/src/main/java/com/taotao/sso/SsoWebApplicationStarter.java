package com.taotao.sso;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

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
