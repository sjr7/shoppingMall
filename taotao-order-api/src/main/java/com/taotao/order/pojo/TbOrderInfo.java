package com.taotao.order.pojo;

import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

import java.util.List;

/**
 * Created by 孙建荣 on 17-6-19.下午9:51
 */
public class TbOrderInfo extends com.taotao.pojo.TbOrder {
    private List<TbOrderItem> orderItems;
    private TbOrderShipping tbOrderShipping;

    public TbOrderInfo() {
    }

    public TbOrderInfo(List<TbOrderItem> orderItems, TbOrderShipping tbOrderShipping) {
        this.orderItems = orderItems;
        this.tbOrderShipping = tbOrderShipping;
    }

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getTbOrderShipping() {
        return tbOrderShipping;
    }

    public void setTbOrderShipping(TbOrderShipping tbOrderShipping) {
        this.tbOrderShipping = tbOrderShipping;
    }
}
