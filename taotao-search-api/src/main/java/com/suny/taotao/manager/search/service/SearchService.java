package com.suny.taotao.manager.search.service;

import com.suny.taotao.common.pojo.SearchResult;

/**
 * Created by 孙建荣 on 17-6-16.上午10:50
 */
public interface SearchService {
    SearchResult search(String queryString, int page, int rows);
}
