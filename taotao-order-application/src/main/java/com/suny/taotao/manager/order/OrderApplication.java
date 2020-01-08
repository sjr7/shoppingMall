package com.suny.taotao.manager.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sunjianrong
 * @date 2020-01-07 15:01
 */
@SpringBootApplication
@EnableDubboConfig
@EnableAsync
public class OrderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(OrderApplication.class).run(args);
    }
}
