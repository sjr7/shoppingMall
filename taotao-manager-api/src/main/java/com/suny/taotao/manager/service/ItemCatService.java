package com.suny.taotao.manager.service;

import com.suny.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * Created by 孙建荣 on 17-6-6.下午11:02
 */
public interface ItemCatService {
    List<EasyUITreeNode> getCatList(long parentId);
}
