package com.taotao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * Created by 孙建荣 on 17-6-16.下午9:54
 */
public class TestActivemq {

    @Test
    public void testQueueProducer() throws JMSException {
        //1. 创建一个连接工厂对象ConnectionFactory对象,需要指定mq服务的ip跟端口
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //2. 使用ConnectionFactory创建一个连接Connection对象
        Connection connection = connectionFactory.createConnection();
        //3. 开启连接，调用Connection对象的start方法
        connection.start();
        //4.使用Connection对象创建一个session对象
        // 第一个参数为：是否开启事物，true为事物，第二个参数忽略
        // 第二个餐胡是第一个参数为true时才有意义，消息的应答模式，
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5.使用session对象创建Destination对象,两种形式queue,topic，现在应该使用queue
        Queue queue = session.createQueue("test-queue");
        // 6.使用session对象创建一个Producer对象
        MessageProducer producer = session.createProducer(queue);
        //7.创建一个TextMessage对象
//        ActiveMQTextMessage message = new ActiveMQTextMessage();
//        message.setText("hello activeMQ");
        TextMessage textMessage = session.createTextMessage("hello activeMq");
        //8.发送消息
        producer.send(textMessage);
        //9.关闭资源
        producer.close();
        session.close();
        connection.close();

    }
}







