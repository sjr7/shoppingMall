package com.suny.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 孙建荣 on 17-6-1.下午8:14
 */
public class EasyUIDataGridResult implements Serializable {
    private long total;

    private List<?> rows;

    public EasyUIDataGridResult() {
    }

    public EasyUIDataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "EasyUIDataGridResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
