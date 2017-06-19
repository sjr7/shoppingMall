package com.taotao.order.controller;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
