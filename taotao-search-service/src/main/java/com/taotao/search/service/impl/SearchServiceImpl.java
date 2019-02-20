package com.taotao.search.service.impl;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 孙建荣 on 17-6-16.上午10:51
 */
@Service
public class SearchServiceImpl implements SearchService {

    private final SearchDao searchDao;

    @Autowired
    public SearchServiceImpl(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    @Override
    public SearchResult search(String queryString, int page, int rows) {
        // 创建一个SolrQuery对象
        SolrQuery solrQuery = new SolrQuery();
        // 设置查询条件
        solrQuery.setQuery(queryString);
        // 设置分页条件
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);
        //   指定搜索域
        solrQuery.set("df", "item_title");
        // 设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        // 执行查询，调用SearchDao，得到SearchResult
        try {
            SearchResult searchResult = searchDao.search(solrQuery);
            // 计算总页数
            long recordCount = searchResult.getRecordCount();
            long pageCount = recordCount / rows;
            if (recordCount % rows > 0) {
                pageCount++;
            }
            searchResult.setPageCount(pageCount);
            // 返回SearchResult
            return searchResult;
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
















