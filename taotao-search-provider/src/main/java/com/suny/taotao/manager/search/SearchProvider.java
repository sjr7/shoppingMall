package com.suny.taotao.manager.search;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sunjianrong
 * @date 2020-01-07 15:59
 */
@SpringBootApplication
@EnableDubboConfig
@EnableAsync
@MapperScan({"com.suny.taotao.**.mapper"})
public class SearchProvider {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SearchProvider.class).web(WebApplicationType.NONE).run(args);
    }
}
