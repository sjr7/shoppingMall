import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 测试redis
 * Created by 孙建荣 on 17-6-14.下午10:05
 */
public class RedisTest {
//    @Test
    public void testJedisCluster() throws IOException {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 6379));
        nodes.add(new HostAndPort("127.0.0.1", 7000));
        nodes.add(new HostAndPort("127.0.0.1", 7001));
        nodes.add(new HostAndPort("127.0.0.1", 7002));
        nodes.add(new HostAndPort("127.0.0.1", 7003));
        nodes.add(new HostAndPort("127.0.0.1", 7004));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        // 第二部使用jedisCluster对象操作redis
        jedisCluster.set("hello", "100");
        String result = jedisCluster.get("hello");
        System.out.println(result);
        // 关闭jedisCluster对象
        jedisCluster.close();
    }

//    @Test
    public void testJedisPool() {
        // 第一步创建一个jedisPool对象
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        // 从池中取出jedis对象
        Jedis jedis = jedisPool.getResource();
        // 使用jedis操作redis服务器
        jedis.set("jedis", "test");
        String result = jedis.get("jedis");
        System.out.println(result);
        // 关闭连接
        jedis.close();
        // 关闭jedisPool对象
        jedisPool.close();
    }

//    @Test
    public void testRedis() {
        // 第一步创建一个jredis对象，需要指定服务器的端口ip
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 第二步jedis操作数据库
        String result = jedis.get("hello");
        // 第三步：打印结果
        System.out.println(result);
        // 第四部，关闭jedis
        jedis.close();
    }
}
