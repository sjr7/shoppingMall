package com.suny.taotao.item;

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
public class TaotaoItemApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TaotaoItemApplication.class).run(args);
    }
}
