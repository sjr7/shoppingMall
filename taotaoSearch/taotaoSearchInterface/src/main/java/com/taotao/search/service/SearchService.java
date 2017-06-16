package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;

/**
 * Created by 孙建荣 on 17-6-16.上午10:50
 */
public interface SearchService {
    SearchResult search(String queryString, int page, int rows);
}
