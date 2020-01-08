package com.suny.taotao.manager.controller;

import com.suny.taotao.common.pojo.EasyUITreeNode;
import com.suny.taotao.manager.service.ItemCatService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品类目
 * Created by 孙建荣 on 17-6-10.上午9:11
 */
@Controller
public class ItemCatController {
    @Reference
    private ItemCatService itemCatService;


    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        return itemCatService.getCatList(parentId);
    }
}
