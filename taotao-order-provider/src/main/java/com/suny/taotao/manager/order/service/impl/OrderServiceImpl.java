package com.suny.taotao.manager.order.service.impl;

import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.manager.mapper.TbOrderItemMapper;
import com.suny.taotao.manager.mapper.TbOrderMapper;
import com.suny.taotao.manager.mapper.TbOrderShippingMapper;
import com.suny.taotao.manager.order.pojo.TbOrderInfo;
import com.suny.taotao.manager.order.service.OrderService;
import com.suny.taotao.manager.pojo.TbOrderItem;
import com.suny.taotao.manager.pojo.TbOrderShipping;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 订单操作业务逻辑实现类
 * Created by 孙建荣 on 17-6-19.下午10:10
 */
@Service
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
    @Value("${ORDER_ID_BEGIN}")
    private String ORDER_ID_BEGIN;
    @Value("${ORDER_ITEM_ID_GEN_KEY}")
    private String ORDER_ITEM_ID_GEN_KEY;


    @Override
    public TaotaoResult createOrder(TbOrderInfo tbOrderInfo) {
        // 接收表单的数据
        // 生成订单
        if (!stringRedisTemplate.hasKey(ORDER_GEN_KEY)) {
            // 设置初始值
            stringRedisTemplate.opsForValue().set(ORDER_GEN_KEY, ORDER_ID_BEGIN);
        }
        String orderId = stringRedisTemplate.opsForValue().increment(ORDER_GEN_KEY).toString();
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
            Long orderItemId = stringRedisTemplate.opsForValue().increment(ORDER_ITEM_ID_GEN_KEY);
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

















