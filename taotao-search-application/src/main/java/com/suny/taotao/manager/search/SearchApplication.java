package com.suny.taotao.manager.search;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sunjianrong
 * @date 2020-01-07 16:37
 */
@SpringBootApplication
@EnableDubboConfig
@EnableAsync
public class SearchApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SearchApplication.class).run(args);
    }

}
