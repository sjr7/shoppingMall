package com.taotao.order.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.jedis.JedisClient;
import com.taotao.order.pojo.TbOrderInfo;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单操作业务逻辑实现类
 * Created by 孙建荣 on 17-6-19.下午10:10
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final TbOrderShippingMapper tbOrderShippingMapper;
    private final TbOrderItemMapper tbOrderItemMapper;
    private final TbOrderMapper tbOrderMapper;
    private final JedisClient jedisClient;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
    @Value("${ORDER_ID_BEGIN}")
    private String ORDER_ID_BEGIN;
    @Value("${ORDER_ITEM_ID_GEN_KEY}")
    private String ORDER_ITEM_ID_GEN_KEY;

    @Autowired
    public OrderServiceImpl(TbOrderMapper tbOrderMapper, TbOrderItemMapper tbOrderItemMapper, TbOrderShippingMapper tbOrderShippingMapper, JedisClient jedisClient) {
        this.tbOrderMapper = tbOrderMapper;
        this.tbOrderItemMapper = tbOrderItemMapper;
        this.tbOrderShippingMapper = tbOrderShippingMapper;
        this.jedisClient = jedisClient;
    }

    @Override
    public TaotaoResult createOrder(TbOrderInfo tbOrderInfo) {
        // 接收表单的数据
        // 生成订单
        if (!jedisClient.exists(ORDER_GEN_KEY)) {
            // 设置初始值
            jedisClient.set(ORDER_GEN_KEY, ORDER_ID_BEGIN);
        }
        String orderId = jedisClient.incr(ORDER_GEN_KEY).toString();
        tbOrderInfo.setOrderId(orderId);
        tbOrderInfo.setPostFee("0");
        // 1.未付款2.已付款3.未发货4.已发货5.发货成功6.交易关闭
        tbOrderInfo.setStatus(1);
        Date date = new Date();
        tbOrderInfo.setCreateTime(date);
        tbOrderInfo.setUpdateTime(date);
        // 向订单中插入数据
        List<TbOrderItem> orderItems = tbOrderInfo.getOrderItems();
        for (TbOrderItem orderItem : orderItems) {
            // 生成订单详细ID
            Long orderItemId = jedisClient.incr(ORDER_ITEM_ID_GEN_KEY);
            orderItem.setId(orderItemId.toString());
            orderItem.setOrderId(orderId);
            // 插入数据
            tbOrderItemMapper.insert(orderItem);
        }
        // 向订单物流插入数据
        TbOrderShipping tbOrderShipping = tbOrderInfo.getTbOrderShipping();
        tbOrderShipping.setOrderId(orderId);
        tbOrderShipping.setCreated(date);
        tbOrderShipping.setUpdated(date);
        tbOrderShippingMapper.insert(tbOrderShipping);
        // 返回TaotaoResult
        return TaotaoResult.ok(orderId);
    }
}

















