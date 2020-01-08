package com.suny.taotao.manager.service.impl;

import com.suny.taotao.common.pojo.EasyUITreeNode;
import com.suny.taotao.manager.mapper.TbItemCatMapper;
import com.suny.taotao.manager.pojo.TbItemCat;
import com.suny.taotao.manager.pojo.TbItemCatExample;
import com.suny.taotao.manager.service.ItemCatService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孙建荣 on 17-6-6.下午11:04
 */
@Service
@Component
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;


    @Override
    public List<EasyUITreeNode> getCatList(long parentId) {
        // 根据parentId查询节点列表
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        // 设置查询条件
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);
        // 转成EasyUITreeNode列表
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbItemCat tbItemCat : tbItemCats) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent() ? "closed" : "opem");
            // 添加到列表
            resultList.add(node);
        }
        // 返回
        return resultList;
    }
}



















