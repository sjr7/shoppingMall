package com.suny.taotao.manager.order.service;

import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.manager.order.pojo.TbOrderInfo;

/**
 * 订单操作业务逻辑接口
 * Created by 孙建荣 on 17-6-19.下午10:07
 */
public interface OrderService {
    TaotaoResult createOrder(TbOrderInfo tbOrderInfo);
}
