package com.taotao.content.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 孙建荣 on 17-6-13.下午10:54
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Value("${INDEX_CONTENT}")
    private String INDEX_CONTENT;

    private final TbContentMapper tbContentMapper;

    private final JedisClient jedisClient;

    @Autowired
    public ContentServiceImpl(TbContentMapper tbContentMapper, JedisClient jedisClient) {
        this.tbContentMapper = tbContentMapper;
        this.jedisClient = jedisClient;
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
        // 先查询缓存
        try {
            // 查询缓存
            String json = jedisClient.hget(INDEX_CONTENT, cid + "");
            // 查询到结果就转成List返回
            if (StringUtils.isNoneBlank(json)) {
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
            // 查询结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 根据cid查询内容列表
        TbContentExample example = new TbContentExample();
        // 设置查询条件
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        // 执行查询
        List<TbContent> list = tbContentMapper.selectByExample(example);
        // 把结果添加到缓存
        try {
            jedisClient.hset(INDEX_CONTENT, cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}











