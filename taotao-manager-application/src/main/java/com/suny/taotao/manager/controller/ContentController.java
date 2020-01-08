package com.suny.taotao.manager.controller;

import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.content.service.ContentService;
import com.suny.taotao.manager.pojo.TbContent;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容管理Controller
 * Created by 孙建荣 on 17-6-13.下午10:50
 */
@Controller
public class ContentController {

    @Reference
    private ContentService contentServiceImpl;


    /**
     * 增加内容
     *
     * @param tbContent 内容实体
     * @return json结果
     */
    @RequestMapping("/content/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent tbContent) {
        return contentServiceImpl.addContent(tbContent);
    }

    /**
     * 增加内容页面
     */
    @RequestMapping("/content-add")
    public String addContentPage() {
        return "content-add";
    }

    /**
     * 进入内容管理主页面
     */
    @RequestMapping("/content")
    public String contentPage() {
        return "content";
    }
}







