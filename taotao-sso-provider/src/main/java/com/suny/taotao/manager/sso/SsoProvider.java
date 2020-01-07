package com.suny.taotao.manager.sso;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sunjianrong
 * @date 2020-01-07 16:58
 */
@EnableAutoConfiguration
@EnableAsync
@EnableTransactionManagement
public class SsoProvider {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SsoProvider.class).run(args);
    }
}
