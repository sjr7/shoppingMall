package com.suny.taotao.manager.service;

import com.suny.taotao.common.pojo.EasyUIDataGridResult;
import com.suny.taotao.common.pojo.TaotaoResult;
import com.suny.taotao.manager.pojo.TbItem;
import com.suny.taotao.manager.pojo.TbItemDesc;

/**
 * Created by 孙建荣 on 17-5-30.下午9:52
 */
public interface ItemService {

    /**
     * 获取商品详情
     *
     * @param itemId 商品ID
     * @return 商品信息
     */
    TbItemDesc getItemDescById(long itemId);


    /**
     * 根据商品Id查询商品
     *
     * @param itemId 商品id
     * @return 商品信息
     */
    TbItem getItemById(long itemId);

    /**
     * 分页查询所有的商品数据
     *
     * @param page 起始页
     * @param rows 起始行
     * @return easyui需要的一个封装数据
     */
    EasyUIDataGridResult getItemList(int page, int rows);

    /**
     * 添加商品操作
     *
     * @param tbItem 商品信息
     * @param desc   解释
     * @return 响应结果
     */
    TaotaoResult addItem(TbItem tbItem, String desc);
}
