package com.taotao.cart.controller;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车管理Controller
 * Created by 孙建荣 on 17-6-19.上午9:44
 */
@Controller
public class CartController {

    @Value("${CART_KEY}")
    private String CART_KEY;
    @Value("${CART_EXPIRE}")
    private Integer CART_EXPIRE;

    private final ItemService itemService;

    @Autowired
    public CartController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/cart/add/{itemId}")
    public String addItemCart(@PathVariable(value = "itemId") Long itemId, @RequestParam(defaultValue = "1") Integer num,
                              HttpServletRequest request, HttpServletResponse response) {
        // 取购物车商品列表
        List<TbItem> cartItemList = getCartItemList(request);
        // 判断商品在购物车是否存在
        boolean flag = false;
        for (TbItem tbItem : cartItemList) {
            if (tbItem.getId() == itemId.longValue()) {
                // 如果存在就数量相加
                tbItem.setNum(tbItem.getNum() + num);
                flag = true;
                break;
            }
        }
        // 如在存在就相加
        if (!flag) {
            // 调用服务查询商品细腻
            TbItem tbItem = itemService.getItemById(itemId);
            // 设置购买数量
            tbItem.setNum(num);
            // 取一张图片
            String image = tbItem.getImage();
            if (StringUtils.isNoneBlank(image)) {
                String[] images = image.split(",");
                tbItem.setImage(images[0]);
            }
            // 商品添加到购物车
            cartItemList.add(tbItem);
        }
        //  如果不存在就相加
        // 需要调用服务取商品信息
        // 设置购买的商品数量
        // 把购物车列表写入cookie
        CookieUtils.setCookie(request, response, CART_KEY, JsonUtils.objectToJson(cartItemList), CART_EXPIRE, true);
        // 返回添加成功页面
        return "cartSuccess";

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













