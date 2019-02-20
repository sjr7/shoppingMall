package com.taotao.search.service.impl;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.mapper.SearchItemMapper;
import com.taotao.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 商品索引实现类
 * Created by 孙建荣 on 17-6-15.下午8:45
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SolrServer solrServer;
    private final SearchItemMapper searchItemMapper;

    @Autowired
    public SearchItemServiceImpl(SearchItemMapper searchItemMapper, SolrServer solrServer) {
        this.searchItemMapper = searchItemMapper;
        this.solrServer = solrServer;
    }

    @Override
    public TaotaoResult importItemToIndex() {
        // 查询商品数据
        List<SearchItem> itemList = searchItemMapper.getItemList();
        // 遍历商品数据到索引库
        for (SearchItem searchItem : itemList) {
            // 创建文档对象
            SolrInputDocument document = new SolrInputDocument();
            // 想文档中添加域
            document.addField("id", searchItem.getId());
            document.addField("item_title", searchItem.getTitle());
            document.addField("item_sell_point", searchItem.getSell_point());
            document.addField("item_price", searchItem.getPrice());
            document.addField("item_image", searchItem.getImages());
            document.addField("item_category_name", searchItem.getCategory_name());
            document.addField("item_desc", searchItem.getItem_desc());
            // 把文档写入到索引库
            try {
                logger.info("开始尝试提交到文档里面,内容:{}", document.toString());
                solrServer.add(document);
            } catch (SolrServerException | IOException e) {
                logger.error("保存到文档里面失败");
                return TaotaoResult.build(500, "数据导入失败");
            }
            // 提交
            try {
                solrServer.commit();
            } catch (SolrServerException | IOException e) {
                logger.error("提交失败");
                return TaotaoResult.build(500, "数据导入失败");
            }
        }
        logger.info("索引成功");
        return TaotaoResult.ok();
    }
}













