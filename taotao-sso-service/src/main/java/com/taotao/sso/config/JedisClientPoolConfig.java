package com.taotao.sso.config;

import com.taotao.sso.jedis.JedisClientPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sunjianrong
 * @date 19-2-23 下午12:05
 */
@Configuration
public class JedisClientPoolConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.host}")
    private int port;

    @Bean
    public JedisPool jedisPool(){
        return new JedisPool(host,port);
    }

    @Bean
    public JedisClientPool jedisClientPool(){
        return new JedisClientPool(jedisPool());
    }

    /**
     *
     * @return redis cluster config bean
     */
    public JedisCluster jedisCluster(){
        AtomicReference<Set<HostAndPort>> hostAndPortSet=new AtomicReference<>(new HashSet<HostAndPort>());
        hostAndPortSet.get().add(new HostAndPort("127.0.0.1",6379));
        hostAndPortSet.get().add(new HostAndPort("127.0.0.1",7000));
        hostAndPortSet.get().add(new HostAndPort("127.0.0.1",7001));
        hostAndPortSet.get().add(new HostAndPort("127.0.0.1",7002));
        hostAndPortSet.get().add(new HostAndPort("127.0.0.1",7003));
        hostAndPortSet.get().add(new HostAndPort("127.0.0.1",7004));
        return new JedisCluster(hostAndPortSet.get());
    }
}
