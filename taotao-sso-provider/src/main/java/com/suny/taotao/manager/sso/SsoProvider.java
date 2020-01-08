package com.suny.taotao.manager.sso;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sunjianrong
 * @date 2020-01-07 16:58
 */
@SpringBootApplication
@EnableDubboConfig
@EnableAsync
@EnableTransactionManagement
@MapperScan({"com.suny.taotao.**.mapper"})
public class SsoProvider {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SsoProvider.class).web(WebApplicationType.NONE).run(args);
    }
}
