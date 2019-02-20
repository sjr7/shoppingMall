package com.taotao.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * FastDFS图片上传工具类
 * Created by 孙建荣 on 17-6-11.上午9:59
 */
public class FastDFSClient {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;

    public FastDFSClient(String conf) {
        if (conf.contains("classpath:")) {
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }
        try {
            ClientGlobal.init(conf);
        } catch (Exception e) {
            logger.error("读取配置文件失败");
            e.printStackTrace();
        }
        trackerClient = new TrackerClient();
        try {
            trackerServer = trackerClient.getConnection();
        } catch (IOException e) {
            logger.error("创建连接失败");
            e.printStackTrace();
        }
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);

    }

    /**
     * 上传文件方法
     *
     * @param fileName 文件的全路径
     * @param extName  文件的扩展名,不包括 .
     * @param metas    文件扩展名信息
     * @return
     */
    private String uploadFile(String fileName, String extName, NameValuePair[] metas) {
        try {
            return storageClient.upload_appender_file1(fileName, extName, metas);
        } catch (Exception e) {
            logger.error("文件上传发生了错误");
            e.printStackTrace();
        }
        return null;
    }

    public String uploadFile(String fileName) {
        return uploadFile(fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     *
     * @param fileContent 文件的内容，为字节数组
     * @param extName     文件的扩展名
     * @param meta        文件的扩展信息
     * @return
     */
    private String uploadFile(byte[] fileContent, String extName, NameValuePair[] meta) {
        try {
            return storageClient.upload_file1(fileContent, extName, meta);
        } catch (Exception e) {
            logger.error("发生了错误");
            e.printStackTrace();
        }
        return null;
    }

    public String uploadFile(byte[] fileContent) {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) {
        return uploadFile(fileContent, extName, null);
    }


}














