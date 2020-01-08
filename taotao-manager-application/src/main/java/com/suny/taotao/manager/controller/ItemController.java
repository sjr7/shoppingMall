package com.suny.taotao.manager.controller;

import com.suny.taotao.common.pojo.EasyUIDataGridResult;
import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.manager.pojo.TbItem;
import com.suny.taotao.manager.service.ItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 孙建荣 on 17-5-30.下午10:23
 */
@Controller
public class ItemController {

    @Reference
    private ItemService itemServiceImpl;


    @RequestMapping("/item-add")
    public String itemAdd() {
        return "item-add";
    }

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        return itemServiceImpl.getItemById(itemId);
    }

    @RequestMapping(value = "/item-list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        return itemServiceImpl.getItemList(page, rows);
    }

    public TaotaoResult addItem(TbItem tbItem, String desc) {
        return itemServiceImpl.addItem(tbItem, desc);
    }


}






















