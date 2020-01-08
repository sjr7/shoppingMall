package com.suny.taotao.manager.portal.controller;

import com.suny.taotao.common.utils.JsonUtils;
import com.suny.taotao.content.service.ContentService;
import com.suny.taotao.manager.pojo.TbContent;
import com.suny.taotao.manager.portal.pojo.AD1Node;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孙建荣 on 17-6-11.下午3:26
 */
@Controller
public class IndexController {

    @Value("${AD1_CATEGORY_ID}")
    private Long AD1_CATEGORY_ID;
    @Value("${AD1_WIDTH}")
    private Integer AD1_WIDTH;
    @Value("${AD1_WIDTH_B}")
    private Integer AD1_WIDTH_B;
    @Value("${AD1_HEIGHT}")
    private Integer AD1_HEIGHT;
    @Value("${AD1_HEIGHT_B}")
    private Integer AD1_HEIGHT_B;

    @Reference
    private ContentService contentServiceImpl;


    @RequestMapping("/index")
    public String showIndex(Model model) {
        // 获取轮播图的信息
        List<TbContent> contentList = contentServiceImpl.getContentById(AD1_CATEGORY_ID);
        // 转换成ADlist
        List<AD1Node> ad1NodeList = new ArrayList<>();
        for (TbContent tbContent : contentList) {
            AD1Node node = new AD1Node();
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHT_B);
            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTH_B);
            node.setHref(tbContent.getUrl());
            node.setSrc(tbContent.getPic());
            node.setSrcB(tbContent.getPic2());
            System.out.println(node.toString());
            // 添加到列表
            ad1NodeList.add(node);
        }
        // 把数据传递给页面
        model.addAttribute("ad1", JsonUtils.objectToJson(ad1NodeList));
        return "index";
    }
}
















