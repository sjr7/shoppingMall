package com.taotao.item.pojo;

import com.taotao.pojo.TbItem;

/**
 * Created by 孙建荣 on 17-6-17.下午12:49
 */
public class Item extends TbItem {
    public Item() {
    }

    public Item(TbItem tbItem) {
        // 初始化属性
        this.setBarcode(tbItem.getBarcode());
        this.setCid(tbItem.getCid());
        this.setCreated(tbItem.getCreated());
        this.setId(tbItem.getId());
        this.setImage(tbItem.getImage());
        this.setNum(tbItem.getNum());
        this.setPrice(tbItem.getPrice());
        this.setSellPoint(tbItem.getSellPoint());
        this.setStatus(tbItem.getStatus());
        this.setTitle(tbItem.getTitle());
        this.setUpdated(tbItem.getUpdated());
    }

    public String[] getImages() {
        if (this.getImage() != null && !"".equals(this.getImage())) {
            String image = this.getImage();
            String[] strings = image.split(",");
            return strings;
        }
        return null;
    }
}
