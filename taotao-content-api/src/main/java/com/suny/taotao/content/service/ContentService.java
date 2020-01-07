package com.suny.taotao.content.service;

import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.manager.pojo.TbContent;

import java.util.List;

/**
 * 内容管理Service借口
 * Created by 孙建荣 on 17-6-13.下午10:52
 */
public interface ContentService {

    TaotaoResult addContent(TbContent tbContent);

    /**
     * 首页广告位
     */
    List<TbContent> getContentById(long cid);
}
