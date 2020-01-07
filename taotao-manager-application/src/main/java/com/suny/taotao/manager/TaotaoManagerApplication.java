package com.suny.taotao.manager;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sunjianrong
 * @date 2020-01-07 14:03
 */
@EnableAutoConfiguration
@EnableAsync
public class TaotaoManagerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TaotaoManagerApplication.class).run(args);
    }
}
