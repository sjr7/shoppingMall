package com.suny.taotao.cart.controller;

import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.common.utils.CookieUtils;
import com.suny.taotao.common.utils.JsonUtils;
import com.suny.taotao.manager.pojo.TbItem;
import com.suny.taotao.manager.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Reference
    private ItemService itemService;


    @RequestMapping(value = "/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        // 从cookie中读取购物车列表
        List<TbItem> cartItemList = getCartItemList(request);
        // 找到对应的商品
        for (TbItem tbItem : cartItemList) {
            if (tbItem.getId() == itemId.longValue()) {
                // 删除商品
                cartItemList.remove(tbItem);
                break;
            }
        }
        // 把商品列表写入cookie
        CookieUtils.setCookie(request, response, CART_KEY, JsonUtils.objectToJson(cartItemList), CART_EXPIRE, true);
        // 返回逻辑视图:在逻辑师徒中做redirect跳转
        return "redirect:/cart/cart.html";
    }

    @RequestMapping(value = "/cart/update/num/{itemId}/{num}")
    public TaotaoResult updateItemNum(@PathVariable Long itemId, @PathVariable Integer num,
                                      HttpServletRequest request, HttpServletResponse response) {
        // 从cookie职工去购物车列表
        List<TbItem> cartItemList = getCartItemList(request);
        // 查询到对应的商品
        for (TbItem tbItem : cartItemList) {
            if (tbItem.getId() == itemId.longValue()) {
                // 更新商品数量
                tbItem.setNum(num);
                break;
            }
        }
        // 把购物车列表写入cookie
        CookieUtils.setCookie(request, response, CART_KEY, JsonUtils.objectToJson(cartItemList), CART_EXPIRE, true);
        // 返回成功
        return TaotaoResult.ok();

    }

    @RequestMapping(value = "/cart/cart")
    public String showCartList(HttpServletRequest request, Model model) {
        // 取得购物车商品列表
        List<TbItem> cartItemList = getCartItemList(request);
        // 传递给页面
        model.addAttribute("cartList", cartItemList);
        return "cart";
    }

    /**
     * 添加商品到购物车
     *
     * @param itemId   商品id
     * @param num      购买数量
     * @param request  请求
     * @param response 响应
     * @return 添加成功页面
     */
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













