package com.suny.taotao.manager.search.mapper;

import com.suny.taotao.common.pojo.SearchItem;

import java.util.List;

/**
 * Created by 孙建荣 on 17-6-15.下午7:30
 */
public interface SearchItemMapper {
    List<SearchItem> getItemList();

    SearchItem getItemItemById(Long itemId);
}
