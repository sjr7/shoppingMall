package com.suny.taotao.manager.search;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sunjianrong
 * @date 2020-01-07 16:37
 */
@EnableAutoConfiguration
@EnableAsync
public class SearchApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SearchApplication.class).run(args);
    }

}
