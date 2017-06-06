package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * Created by 孙建荣 on 17-6-6.下午11:02
 */
public interface ItemCatService {
    public List<EasyUITreeNode> getCatList(long parentId);
}
