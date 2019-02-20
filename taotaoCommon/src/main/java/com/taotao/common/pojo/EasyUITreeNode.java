package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * Created by 孙建荣 on 17-6-6.下午10:46
 */
public class EasyUITreeNode implements Serializable {

    private long id;
    private String text;
    private String state;

    public EasyUITreeNode() {
    }

    public EasyUITreeNode(long id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
