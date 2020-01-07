package com.suny.taotao.common.utils;

import java.util.Random;

/**
 * 生成各种各样的主键ID
 * Created by 孙建荣 on 17-6-11.下午2:23
 */
public class IDUtils {

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0

        return millis + String.format("%03d", end3);
    }

    /**
     * 商品id生成
     */
    public static long genItemId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        return new Long(str);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++)
            System.out.println(genItemId());
    }
}
