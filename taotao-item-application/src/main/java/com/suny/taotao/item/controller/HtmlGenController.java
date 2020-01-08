package com.suny.taotao.item.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 网页静态化处理Controller
 * Created by 孙建荣 on 17-6-17.下午4:24
 */
@Controller
public class HtmlGenController {
    @Reference
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping(value = "genHtml.html")
    @ResponseBody
    public String genHtml() throws IOException, TemplateException {
        // 生成静态页面
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("hello.ftl");
        // 创建数据集
        Map date = new HashMap();
        date.put("hello", "Spring freeMaker");
        Writer out = new FileWriter(new File("/home/sunjianrong/IdeaProjects/shoppingMall/taotaoItemWeb/src/test/resources/freeMarkerOut/spring.html"));
        template.process(date, out);
        // 关闭流
        out.close();
        // 返回结果
        return "OK";

    }
}
