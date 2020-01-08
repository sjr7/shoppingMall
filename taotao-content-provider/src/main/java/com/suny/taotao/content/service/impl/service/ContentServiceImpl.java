package com.suny.taotao.content.service.impl.service;

import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.common.utils.JsonUtils;
import com.suny.taotao.content.service.ContentService;
import com.suny.taotao.manager.mapper.TbContentMapper;
import com.suny.taotao.manager.pojo.TbContent;
import com.suny.taotao.manager.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 孙建荣 on 17-6-13.下午10:54
 */
@Service
@Component
public class ContentServiceImpl implements ContentService {
    @Value("${INDEX_CONTENT}")
    private String INDEX_CONTENT;

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        // 补全属性
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        // 插入数据
        tbContentMapper.insert(tbContent);
        // 添加redis缓存同步
        stringRedisTemplate.opsForHash().delete(INDEX_CONTENT, tbContent.getCategoryId().toString());
        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentById(long cid) {
        // 先查询缓存
        try {
            // 查询缓存
            Object json = stringRedisTemplate.opsForHash().get(INDEX_CONTENT, cid + "");
            // 查询到结果就转成List返回
            if (json != null && StringUtils.isNotBlank(json.toString())) {
                List<TbContent> list = JsonUtils.jsonToList(json.toString(), TbContent.class);
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
            stringRedisTemplate.opsForHash().put(INDEX_CONTENT, cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}











