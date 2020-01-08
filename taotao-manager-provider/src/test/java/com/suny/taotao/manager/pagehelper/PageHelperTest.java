package com.suny.taotao.manager.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suny.taotao.manager.mapper.TbItemMapper;
import com.suny.taotao.manager.pojo.TbItem;
import com.suny.taotao.manager.pojo.TbItemExample;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by 孙建荣 on 17-6-1.下午10:45
 */
public class PageHelperTest {

//    @Test
    public void test() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        // 获取mapper代理对象
        TbItemMapper bean = classPathXmlApplicationContext.getBean(TbItemMapper.class);
        // 设置分页信息
        PageHelper.startPage(1, 30);
        // 执行查询
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItems = bean.selectByExample(tbItemExample);
        // 获取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo(tbItems);

    }


}