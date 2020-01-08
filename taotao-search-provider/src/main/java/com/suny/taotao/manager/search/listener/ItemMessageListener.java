package com.suny.taotao.manager.search.listener;

import com.suny.taotao.common.pojo.SearchItem;
import com.suny.taotao.manager.search.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * 添加商品添加事件，添加索引库
 * Created by 孙建荣 on 17-6-17.上午9:35
 */
public class ItemMessageListener implements MessageListener {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private SearchItemMapper searchItemMapper;


    @Override
    public void onMessage(Message message) {
        // 取商品ID
        TextMessage textMessage = (TextMessage) message;
        // 根据商品ID查询数据库。取出商品信息
        try {
            String text = textMessage.getText();
            long itemId = Long.parseLong(text);
            // 等待事物提交
            Thread.sleep(1000);
            SearchItem searchItem = searchItemMapper.getItemItemById(itemId);
            // 创建文档对象
            SolrInputDocument document = new SolrInputDocument();
            //向文档对象添加域
            document.addField("id", searchItem.getId());
            document.addField("item_title", searchItem.getTitle());
            document.addField("item_sell_point", searchItem.getSell_point());
            document.addField("item_price", searchItem.getPrice());
            document.addField("item_image", searchItem.getImages());
            document.addField("item_category_name", searchItem.getCategory_name());
            document.addField("item_desc", searchItem.getItem_desc());
            // 把文件对象写入索引库
            solrClient.add(document);
            // 提交
            solrClient.commit();
        } catch (JMSException | InterruptedException | SolrServerException | IOException e) {
            e.printStackTrace();
        }

    }
}











