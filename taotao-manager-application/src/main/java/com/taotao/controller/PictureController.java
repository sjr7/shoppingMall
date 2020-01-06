package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器
 * Created by 孙建荣 on 17-6-11.上午10:41
 */
@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping(value = "/pic/upload", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    @ResponseBody
    public String fileUpload(MultipartFile uploadFile) {
        try {
            // 获取文件的扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            // 创建一个 FastDFS的客户端
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/client.conf");
            // 执行上传处理
            String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            // 凭借返回的url跟ip地址
            String url = IMAGE_SERVER_URL + path;
            // 返回MAP
            Map result = new HashMap<>();
            result.put("error", 0);
            result.put("url", url);
            // 把java对象转换成字符串
            return JsonUtils.objectToJson(result);
        } catch (IOException e) {
            e.printStackTrace();
            // 错误也要返回map
            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            // 把java对象转换成字符串
            return JsonUtils.objectToJson(result);
        }
    }
}
















