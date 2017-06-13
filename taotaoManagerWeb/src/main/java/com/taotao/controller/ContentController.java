package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容管理Controller
 * Created by 孙建荣 on 17-6-13.下午10:50
 */
@Controller
public class ContentController {

    private final ContentService contentServiceImpl;

    @Autowired
    public ContentController(ContentService contentServiceImpl) {
        this.contentServiceImpl = contentServiceImpl;
    }

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
     * 进入内容管理主页面
     */
    @RequestMapping("/content")
    public String contentPage() {
        return "content";
    }
}







