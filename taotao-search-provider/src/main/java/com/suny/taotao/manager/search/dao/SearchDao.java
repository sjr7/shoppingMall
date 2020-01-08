package com.suny.taotao.manager.search.dao;

import com.suny.taotao.common.pojo.SearchItem;
import com.suny.taotao.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询索引库商品dao
 * Created by 孙建荣 on 17-6-15.下午10:56
 */
@Repository
public class SearchDao {

    @Autowired
    private SolrClient solrClient;

    public SearchResult search(SolrQuery solrQuery) throws SolrServerException, IOException {
        // 根据条件进行查询
        QueryResponse response = solrClient.query(solrQuery);
        // 取查询结果
        SolrDocumentList documentList = response.getResults();
        // 商品列表
        List<SearchItem> itemList = new ArrayList<>();
        for (SolrDocument solrDocument : documentList) {
            SearchItem item = new SearchItem();
            item.setId((String) solrDocument.get("id"));
            item.setCategory_name((String) solrDocument.get("item_category_name"));
            item.setImages((String) solrDocument.get("item_images"));
            item.setPrice((Long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));
            // 高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle;
            // 有高亮时显示内容
            if (list != null && list.size() > 0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            item.setTitle(itemTitle);
            // 添加到商品列表
            itemList.add(item);
        }
        SearchResult searchResult = new SearchResult();
        // 商品列表
        searchResult.setItemList(itemList);
        // 总记录数
        searchResult.setRecordCount(documentList.getNumFound());
        return searchResult;
    }
}












