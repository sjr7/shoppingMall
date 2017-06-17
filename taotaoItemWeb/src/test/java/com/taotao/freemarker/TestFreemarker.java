package com.taotao.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * Created by 孙建荣 on 17-6-17.下午3:24
 */
public class TestFreemarker {


    @Test
    public void testFreeMarker() throws IOException {
        // 创建一个模板文件
        // 创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板所在的路径
        configuration.setDirectoryForTemplateLoading(new File("/home/sunjianrong/IdeaProjects/shoppingMall/taotaoItemWeb/src/test/resources/ftl"));
        // 设置模板的字符集
        configuration.setDefaultEncoding("utf-8");
        // 使用Configuration对象加载一个模板
        Template template = configuration.getTemplate("studentList.ftl");
        // 创建一个数据集,可以是pojo也可以是map
        Map dataModal = new HashMap<>();
        // 添加数据
        dataModal.put("hello", "this is my frist freemarket test");
        List list = new ArrayList();
        Student student = new Student(1, "测试名字", 19, "江西省南昌市");
        Student student2 = new Student(1222, "测试名字2", 19, "江西省南昌市22222222");
        list.add(student);
        list.add(student2);
        dataModal.put("list", list);
        dataModal.put("date", new Date());
        // 创建一个Writer对象,指定输出的文件路径以及文件名
        Writer out = new FileWriter(new File("/home/sunjianrong/IdeaProjects/shoppingMall/taotaoItemWeb/src/test/resources/freeMarkerOut/studentList.html"));
        // 使用模板对象的process方法输出文件
        try {
            template.process(dataModal, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        // 关闭流
        out.close();
    }
}
