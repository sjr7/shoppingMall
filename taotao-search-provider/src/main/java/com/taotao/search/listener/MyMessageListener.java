package com.taotao.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 接收Activemq发送的消息
 * Created by 孙建荣 on 17-6-17.上午9:11
 */
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            // 接收到消息
            TextMessage textMessage = (TextMessage) message;
            String text = null;
            text = textMessage.getText();
            System.out.println(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
