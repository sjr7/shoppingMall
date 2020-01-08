package com.suny.taotao.manager;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sunjianrong
 * @date 2020-01-07 14:29
 */
@SpringBootApplication
@EnableDubboConfig
@EnableAsync
@MapperScan({"com.suny.taotao.**.mapper"})
public class TaotaoManagerProvider {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TaotaoManagerProvider.class).web(WebApplicationType.NONE).run(args);
    }

}
