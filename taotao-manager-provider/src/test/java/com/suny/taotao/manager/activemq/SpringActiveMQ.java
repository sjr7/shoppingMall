package com.suny.taotao.manager.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;


/**
 * 测试Spring整合ActiveMQ
 * Created by 孙建荣 on 17-6-17.上午9:00
 */
public class SpringActiveMQ {

    //    @Test
    public void testQueueProduce() {
        //  第一步初始化Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activeMQ.xml");
        // 第二部 从容器中获取JMSTemplate对象
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        // 第三步
        /*Queue queue = (Queue) applicationContext.getBean("activeMQQueue");
        // 第四步 使用JmsTemplate发送消息，需要知道Destination
        jmsTemplate.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("spring active test");
            }
        });*/
    }
}













