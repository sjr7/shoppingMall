package com.suny.taotao.manager.order.pojo;

import com.suny.taotao.manager.pojo.TbOrder;
import com.suny.taotao.manager.pojo.TbOrderItem;
import com.suny.taotao.manager.pojo.TbOrderShipping;

import java.util.List;

/**
 * Created by 孙建荣 on 17-6-19.下午9:51
 */
public class TbOrderInfo extends TbOrder {
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
