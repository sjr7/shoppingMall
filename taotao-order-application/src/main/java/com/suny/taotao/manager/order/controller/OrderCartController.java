package com.suny.taotao.manager.order.controller;

import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.common.utils.CookieUtils;
import com.suny.taotao.common.utils.JsonUtils;
import com.suny.taotao.manager.order.pojo.TbOrderInfo;
import com.suny.taotao.manager.order.service.OrderService;
import com.suny.taotao.manager.pojo.TbItem;
import com.suny.taotao.manager.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单系统控制器
 * Created by 孙建荣 on 17-6-19.下午5:21
 */
@Controller
public class OrderCartController {

    @Value("${CART_KEY}")
    private String CART_KEY;

    @Reference
    private OrderService orderService;


    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public String createOrder(TbOrderInfo orderInfo, HttpServletRequest request) {
        // 接收表单提交的数据OrderInfo
        // 补全用户信息
        TbUser tbUser = (TbUser) request.getAttribute("user");
        orderInfo.setUserId(tbUser.getId());
        orderInfo.setBuyerNick(tbUser.getUsername());
        // 调用service创建订单
        TaotaoResult result = orderService.createOrder(orderInfo);
        // 取到订单号
        String orderId = result.getData().toString();
        // 使用Service返回订单号
        request.setAttribute("orderId", orderId);
        request.setAttribute("payment", orderInfo.getPayment());
        // 当前日期加三天
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(3);
        request.setAttribute("date", dateTime.toString("yyyy-MM-dd"));
        // 返回逻辑师徒展示成功页面
        return "success";
    }

    @RequestMapping(value = "/order/order-cart")
    public String showOrderCart(HttpServletRequest request) {
        // 用户必须是登录状态
        // 获取用户ID
        TbUser tbUser = (TbUser) request.getAttribute("user");
        System.out.println(tbUser.getUsername());
        // 根据用户信息取出收货地址,使用静态数据
        // 把收货地址传递给页面
        // 从cookie中取出购物车商品展示给页面
        List<TbItem> cartItemList = getCartItemList(request);
        request.setAttribute("cartList", cartItemList);
        // 返回逻辑视图
        return "order-cart";
    }

    private List<TbItem> getCartItemList(HttpServletRequest request) {
        // 从cookie中取购物车商品列表
        String json = CookieUtils.getCookieValue(request, CART_KEY, true);
        if (StringUtils.isBlank(json)) {
            // 如果没有内容就返回一个空的列表
            return new ArrayList<>();
        }
        return JsonUtils.jsonToList(json, TbItem.class);
    }
}
