package com.suny.taotao.content.service.impl;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sunjianrong
 * @date 2020-01-06 16:46
 */
@SpringBootApplication
@EnableDubboConfig
@EnableTransactionManagement
@EnableAsync
@MapperScan({"com.suny.taotao.**.mapper"})
public class TaotaoContentProvider {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TaotaoContentProvider.class).web(WebApplicationType.NONE)
                .run(args);
    }
}
