package com.taotao.item.listener;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 孙建荣 on 17-6-17.下午4:57
 */
public class ItemAddMesssageListener implements MessageListener {
    @Value("${HTML_OUT_PATH}")
    private String HTML_OUT_PATH;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private ItemService itemService;

    @Override
    public void onMessage(Message message) {
        // 从消息中取出商品ID
        TextMessage textMessage = (TextMessage) message;
        try {
            // 根据商品ID查询商品信息以及商品描述
            String strId = textMessage.getText();
            long itemId = Long.parseLong(strId);
            // 等待事物的提交
            Thread.sleep(1000);
            TbItem tbItem = itemService.getItemById(itemId);
            Item item = new Item(tbItem);
            TbItemDesc itemDesc = itemService.getItemDescById(itemId);
            // 使用freemarker生成静态页面
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            // 1.创建模板
            Template template = configuration.getTemplate("item.ftl");
            // 2.加载模板
            Map data = new HashMap<>();
            data.put("item", item);
            data.put("itemDesc", itemDesc);
            // 3.准备模板需要的数据
            // 4.输出的目录以及文件名
            Writer out = new FileWriter(new File(HTML_OUT_PATH + strId + ".html"));
            // 5.生成静态页面
            template.process(data, out);
            //关闭资源
            out.close();
        } catch (JMSException | IOException | TemplateException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}













