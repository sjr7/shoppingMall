package com.taotao.item.controller;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品详情页面控制器
 * Created by 孙建荣 on 17-6-17.下午1:01
 */
@Controller
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable("itemId") long itemId, Model model) {
        // 取商品基本信息
        TbItem tbItem = itemService.getItemById(itemId);
        Item item = new Item(tbItem);
        // 取商品详情
        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
        // 把数据传递给页面
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", itemDesc);
        return "/WEB-INF/jsp/item.ftl";
    }


}
