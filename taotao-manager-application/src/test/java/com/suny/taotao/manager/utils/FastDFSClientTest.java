package com.suny.taotao.manager.utils;

import org.junit.Test;

/**
 * Created by 孙建荣 on 17-6-11.上午10:32
 */
public class FastDFSClientTest {
    @Test
    public void uploadFIle() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("/home/sunjianrong/IdeaProjects/shoppingMall/taotaoManagerWeb/src/main/resources/resource/client.conf");
        String file = fastDFSClient.uploadFile("/home/sunjianrong/IdeaProjects/shoppingMall/taotaoManagerWeb/src/main/webapp/WEB-INF/jsp/item-param-list.jsp");
        System.out.println(file);
    }

}