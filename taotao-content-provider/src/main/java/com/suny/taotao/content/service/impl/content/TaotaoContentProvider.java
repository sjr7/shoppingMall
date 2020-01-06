package com.suny.taotao.content.service.impl.content;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sunjianrong
 * @date 2020-01-06 16:46
 */
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableAsync
public class TaotaoContentProvider {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TaotaoContentProvider.class).web(WebApplicationType.SERVLET)
                .run(args);
    }
}
