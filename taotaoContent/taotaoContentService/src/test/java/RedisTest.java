import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * 测试redis
 * Created by 孙建荣 on 17-6-14.下午10:05
 */
public class RedisTest {
    @Test
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
