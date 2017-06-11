package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容管理service实现
 * Created by 孙建荣 on 17-6-11.下午9:28
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    private final TbContentCategoryMapper tbContentCategoryMapper;

    @Autowired
    public ContentCategoryServiceImpl(TbContentCategoryMapper tbContentCategoryMapper) {
        this.tbContentCategoryMapper = tbContentCategoryMapper;
    }

    @Override
    public List<EasyUITreeNode> getContentCategroyList(long parentId) {
        // 根据参数进行查询
        TbContentCategoryExample example = new TbContentCategoryExample();
        // 设置查询条件
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        // 执行查询
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        // 把列表转换为树型结果
        List<EasyUITreeNode> easyUITreeNodes = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            // 添加到列表
            easyUITreeNodes.add(node);
        }
        return easyUITreeNodes;
    }
}




















