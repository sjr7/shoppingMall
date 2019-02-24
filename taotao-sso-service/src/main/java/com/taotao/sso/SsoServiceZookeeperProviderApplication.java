package com.taotao.sso;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

/**
 * sso service provide application
 * @author sunjianrong
 * @date 19-2-23 下午3:45
 */
@SpringBootApplication
@EnableDubboConfig
public class SsoServiceZookeeperProviderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SsoServiceZookeeperProviderApplication.class)
                .listeners((ApplicationListener<ApplicationEnvironmentPreparedEvent>) SsoServiceZookeeperProviderApplication::onApplicationEvent)
                .run(args);
    }

    private static void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        Environment environment = event.getEnvironment();
        int port = environment.getProperty("embedded.zookeeper.port", int.class);
        new EmbeddedZooKeeper(port, false).start();
    }
}
