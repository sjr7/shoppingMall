package com.suny.taotao.manager.portal;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sunjianrong
 * @date 2020-01-07 15:47
 */
@SpringBootApplication
@EnableDubboConfig
@EnableAsync
public class PortalApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PortalApplication.class).run(args);
    }

}
