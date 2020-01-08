package com.suny.taotao.manager.controller;

import com.suny.taotao.common.pojo.EasyUITreeNode;
import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.content.service.ContentCategoryService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容管理Controller
 * Created by 孙建荣 on 17-6-11.下午9:50
 */
@RequestMapping("/content/category")
@Controller
public class ContentCategoryController {

    @Reference
    private ContentCategoryService contentCategoryServiceImpl;


    /**
     * 创建一个新的分类内容
     *
     * @param parentId 父节点的ID
     * @param name     内容名字
     * @return json结果数据
     */
    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createCategory(Long parentId, String name) {
        return contentCategoryServiceImpl.addContentCategory(parentId, name);
    }

    /**
     * 展现内容列表
     *
     * @param parentId 父节点的ID
     * @return 节点列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
        return contentCategoryServiceImpl.getContentCategroyList(parentId);
    }

    /**
     * 进入内容管理页面
     *
     * @return 内容管理页面
     */
    @RequestMapping("/content-category")
    public String contentCategory() {
        return "content-category";
    }


}












