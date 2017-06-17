import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by 孙建荣 on 17-6-17.上午9:17
 */
public class testQueueConsumer {
//    @Test
    public void testQueueConsumer(){
        // 初始化Spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activeMQ.xml");
        // 等待
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
