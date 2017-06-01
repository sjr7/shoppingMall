package com.taotao.common.pojo;

import java.util.List;

/**
 * Created by 孙建荣 on 17-6-1.下午8:14
 */
public class EasyUIDataGridResult {
    private Integer total;

    private List<?> rows;

    public EasyUIDataGridResult() {
    }

    public EasyUIDataGridResult(Integer total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EasyUIDataGridResult(Long total, List<?> rows) {
        this.total = total.intValue();
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
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
