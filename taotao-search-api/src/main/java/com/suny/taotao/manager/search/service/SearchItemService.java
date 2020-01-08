package com.suny.taotao.manager.search.service;

import com.suny.taotao.common.pojo.TaotaoResult;

/**
 * 商品数据导入索引库接口
 * Created by 孙建荣 on 17-6-15.下午8:44
 */
public interface SearchItemService {
    TaotaoResult importItemToIndex();
}
