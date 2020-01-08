package com.suny.taotao.cart;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sunjianrong
 * @date 2020-01-06 16:46
 */
@SpringBootApplication
@EnableDubboConfig
@EnableAsync
public class TaotaoCartApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TaotaoCartApplication.class).run(args);
    }
}
