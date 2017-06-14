package com.taotao.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 孙建荣 on 17-6-14.下午10:58
 */
public class JedisClientTest {

    @Test
    public void JedisClientTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        // 从容器中获取ｊｅｄｉｓＣｌｉｅｎｔ对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("first", "100");
        String result = jedisClient.get("list");
        System.out.println(result);
    }

}