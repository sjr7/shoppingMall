package com.suny.taotao.cart;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sunjianrong
 * @date 2020-01-06 16:46
 */
@EnableAutoConfiguration
@EnableAsync
public class TaotaoCartApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TaotaoCartApplication.class).run(args);
    }
}
