package com.suny.taotao.content.service.impl.content.service;

import com.suny.taotao.content.service.ContentCategoryService;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public TaotaoResult addContentCategory(long parentId, String name) {
        // 接受两个参数
        // 创建一个content_category对象
        TbContentCategory tbContentCategory = new TbContentCategory();
        // 补全属性
        tbContentCategory.setIsParent(false);
        tbContentCategory.setName(name);
        tbContentCategory.setParentId(parentId);
        // 排序序号，标示同级别的展现次序
        tbContentCategory.setSortOrder(1);
        // 状态
        tbContentCategory.setStatus(1);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        // 插入数据
        tbContentCategoryMapper.insert(tbContentCategory);
        // 判断父节点的isparent是否为true，不是的话改为true
        TbContentCategory parentNode = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parentNode.getIsParent()) {
            parentNode.setIsParent(true);
            // 更新父节点
            tbContentCategoryMapper.updateByPrimaryKey(parentNode);
        }
        // 需要主键返回
        return TaotaoResult.ok(tbContentCategory);
    }
}




















