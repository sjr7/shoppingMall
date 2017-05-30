package com.taotao.service;

import com.taotao.pojo.TbItem;

/**
 * Created by 孙建荣 on 17-5-30.下午9:52
 */
public interface ItemService {

    /**
     * 根据商品Id查询商品
     *
     * @param itemId  商品id
     * @return  商品信息
     */
    TbItem getItemById(long itemId);
}
