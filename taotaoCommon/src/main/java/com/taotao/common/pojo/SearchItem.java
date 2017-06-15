package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * Created by 孙建荣 on 17-6-15.下午8:12
 */
public class SearchItem implements Serializable {
    private String id;
    private String title;
    private String sell_point;
    private long price;
    private String images;
    private String category_name;
    private String item_desc;

    public SearchItem() {
    }

    public SearchItem(String id, String title, String sell_point, long price, String images, String category_name, String item_desc) {
        this.id = id;
        this.title = title;
        this.sell_point = sell_point;
        this.price = price;
        this.images = images;
        this.category_name = category_name;
        this.item_desc = item_desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }
}












