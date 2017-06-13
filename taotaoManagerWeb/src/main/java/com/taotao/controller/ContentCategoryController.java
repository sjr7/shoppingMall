package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ContentCategoryService contentCategoryServiceImpl;

    @Autowired
    public ContentCategoryController(ContentCategoryService contentCategoryServiceImpl) {
        this.contentCategoryServiceImpl = contentCategoryServiceImpl;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createCategory(Long parentId, String name) {
        return contentCategoryServiceImpl.addContentCategory(parentId, name);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
        return contentCategoryServiceImpl.getContentCategroyList(parentId);
    }

    @RequestMapping("/content-category")
    public String contentCategory() {
        return "content-category";
    }


}












