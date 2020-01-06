package com.suny.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * 内容管理service实现类
 * 内容分类管理service接口
 * Created by 孙建荣 on 17-6-11.下午9:24
 */
public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCategroyList(long parentId);

    TaotaoResult addContentCategory(long parentId, String name);

}


























