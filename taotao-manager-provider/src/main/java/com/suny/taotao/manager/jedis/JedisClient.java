package com.suny.taotao.manager.jedis;

/**
 * 常用的redis方法接口
 * Created by 孙建荣 on 17-6-14.下午10:44
 */
public interface JedisClient {
    String set(String key, String value);

    String get(String key);

    Boolean exists(String key);

    Long expire(String key, int seconds);

    Long ttl(String key);

    Long incr(String key);

    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hdel(String key, String... field);
}
