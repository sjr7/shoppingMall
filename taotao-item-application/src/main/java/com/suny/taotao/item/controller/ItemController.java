package com.suny.taotao.item.controller;

import com.suny.taotao.item.pojo.Item;
import com.suny.taotao.manager.pojo.TbItem;
import com.suny.taotao.manager.pojo.TbItemDesc;
import com.suny.taotao.manager.service.ItemService;
import org.apache.dubbo.config.annotation.Reference;
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
    @Reference
    private ItemService itemService;


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
        return "item";
    }


}
