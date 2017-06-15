package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索结果
 * Created by 孙建荣 on 17-6-15.下午10:08
 */
public class SearchResult implements Serializable {

    private static final long serialVersionUID = -618502655573468798L;
    private int totalPages;
    private List<SearchItem> itemList;

    public SearchResult() {
    }

    public SearchResult(int totalPages, List<SearchItem> itemList) {
        this.totalPages = totalPages;
        this.itemList = itemList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }
}
