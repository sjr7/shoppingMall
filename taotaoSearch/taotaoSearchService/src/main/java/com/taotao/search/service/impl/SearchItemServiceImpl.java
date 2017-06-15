package com.taotao.search.service.impl;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.mapper.SearchItemMapper;
import com.taotao.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * 商品索引实现类
 * Created by 孙建荣 on 17-6-15.下午8:45
 */
public class SearchItemServiceImpl implements SearchItemService {
    private final SolrServer solrServer;
    private final SearchItemMapper searchItemMapper;

    @Autowired
    public SearchItemServiceImpl(SearchItemMapper searchItemMapper, SolrServer solrServer) {
        this.searchItemMapper = searchItemMapper;
        this.solrServer = solrServer;
    }

    @Override
    public TaotaoResult importItemTOIndex() {
        // 查询商品数据
        List<SearchItem> itemList = searchItemMapper.getItemList();
        // 遍历商品数据到索引库
        for (SearchItem searchItem : itemList) {
            // 创建文档对象
            SolrInputDocument document = new SolrInputDocument();
            // 想文档中添加域
            document.addField("id", searchItem.getId());
            document.addField("item_title", searchItem.getId());
            document.addField("item_sell_point", searchItem.getId());
            document.addField("item_price", searchItem.getTitle());
            document.addField("item_image", searchItem.getImages());
            document.addField("item_category_name", searchItem.getCategory_name());
            document.addField("item_desc", searchItem.getItem_desc());
            // 把文档写入到索引库
            try {
                solrServer.add(document);
            } catch (SolrServerException | IOException e) {
                return TaotaoResult.build(500, "数据导入失败");
            }
            // 提交
            try {
                solrServer.commit();
            } catch (SolrServerException | IOException e) {
                return TaotaoResult.build(500, "数据导入失败");
            }
        }
        return TaotaoResult.ok();
    }
}













