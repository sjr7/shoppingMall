package com.suny.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索结果
 * Created by 孙建荣 on 17-6-15.下午10:08
 */
public class SearchResult implements Serializable {

    private static final long serialVersionUID = -618502655573468798L;
    private long  recordCount;
    private List<SearchItem> itemList;
    private long pageCount;

    public SearchResult() {
    }

    public SearchResult(long recordCount, List<SearchItem> itemList, long pageCount) {
        this.recordCount = recordCount;
        this.itemList = itemList;
        this.pageCount = pageCount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "recordCount=" + recordCount +
                ", itemList=" + itemList +
                ", pageCount=" + pageCount +
                '}';
    }
}
