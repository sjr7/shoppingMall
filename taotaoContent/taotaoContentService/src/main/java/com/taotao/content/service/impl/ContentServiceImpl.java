package com.taotao.content.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 孙建荣 on 17-6-13.下午10:54
 */
@Service
public class ContentServiceImpl implements ContentService {
    private final TbContentMapper tbContentMapper;

    @Autowired
    public ContentServiceImpl(TbContentMapper tbContentMapper) {
        this.tbContentMapper = tbContentMapper;
    }

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        // 补全属性
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        // 插入数据
        tbContentMapper.insert(tbContent);
        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentById(long cid) {
        // 根据cid查询内容列表
        TbContentExample example = new TbContentExample();
        // 设置查询条件
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        // 执行查询
        return tbContentMapper.selectByExample(example);
    }
}











