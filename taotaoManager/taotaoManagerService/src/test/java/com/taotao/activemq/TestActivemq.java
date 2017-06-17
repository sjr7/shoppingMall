package com.taotao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Created by 孙建荣 on 17-6-16.下午9:54
 */
public class TestActivemq {

    //    @Test
    public void testQueueProducer() throws JMSException {
        // 测试Producer
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

//    @Test
    public void testQueueConsumer() throws JMSException {
        //   第一步：创建一个ConnectionFactory对象。
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //   第二步：从ConnectionFactory对象中获得一个Connection对象。
        Connection connection = connectionFactory.createConnection();
        //   第三步：开启连接。调用Connection对象的start方法。
        connection.start();
        //   第四步：使用Connection对象创建一个Session对象。
        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //   第五步：使用Session对象创建一个Destination对象。和发送端保持一致queue，并且队列的名称一致。
        Queue queue = session.createQueue("test-queue");
        //   第六步：使用Session对象创建一个Consumer对象。
        MessageConsumer consumer = session.createConsumer(queue);
        //   第七步：接收消息。
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                String text = null;
                // 取消息的内容
                try {
                    text = textMessage.getText();
                    // 打印消息
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        // 等待键盘输入
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //   第九步：关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

//    @Test
    public void testTopicProduce() throws JMSException {
        // 第一步：创建ConnectionFactory对象，需要指定服务端ip及端口号。
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        // 第二步：使用ConnectionFactory对象创建一个Connection对象。
        Connection connection = connectionFactory.createConnection();
        // 第三步：开启连接，调用Connection对象的start方法。
        connection.start();
        // 第四步：使用Connection对象创建一个Session对象。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 第五步：使用Session对象创建一个Destination对象（topic、queue），此处创建一个Topic对象。
        Topic topic = session.createTopic("test-topic");
        // 第六步：使用Session对象创建一个Producer对象。
        MessageProducer producer = session.createProducer(topic);
        // 第七步：创建一个Message对象，创建一个TextMessage对象。
        TextMessage textMessage = session.createTextMessage("hell activeMq,this is my topic test");
        // 第八步：使用Producer对象发送消息。
        producer.send(textMessage);
        // 第九步：关闭资源。
        producer.close();
        session.close();
        connection.close();
    }


//    @Test
    public void testTopicConsumer() throws JMSException, IOException {
        // 消费者：接收消息。
        // 第一步：创建一个ConnectionFactory对象。
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        // 第二步：从ConnectionFactory对象中获得一个Connection对象。
        Connection connection = connectionFactory.createConnection();
        // 第三步：开启连接。调用Connection对象的start方法。
        connection.start();
        // 第四步：使用Connection对象创建一个Session对象。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 第五步：使用Session对象创建一个Destination对象。和发送端保持一致topic，并且话题的名称一致。
        Topic topic = session.createTopic("test-topic");
        // 第六步：使用Session对象创建一个Consumer对象。
        MessageConsumer consumer = session.createConsumer(topic);
        // 第七步：接收消息。
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                String text = null;
                // 取消息的内容
                try {
                    text = textMessage.getText();
                    // 第八步 打印出消息
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("topic的消费端");
        // 第八步：打印消息。
        System.in.read();
        // 第九步：关闭资源
        consumer.close();
        session.close();
        connection.close();
    }


}
























