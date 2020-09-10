package com.mbry.IronMan.Utils;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;

import org.springframework.beans.factory.annotation.Value;

public class ImageAPIUtil {

    @Value("{tencent.cos.secretId")
    private static String secretId;

    @Value("{tencent.cos.secretKy")
    private static String secretKey;

    @Value("{tencent.cos.region")
    private static String region;

    @Value("{tencent.cos.bucket")
    private static String bucket;

    private static String key = UUID.randomUUID().toString().replace("-", "");

    // 1 初始化用户身份信息(secretId, secretKey)
    static COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
    // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
    // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
    static ClientConfig clientConfig = new ClientConfig(new Region(region));
    // 3 生成cos客户端
    static COSClient cosClient = new COSClient(cred, clientConfig);
    // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
    // 指定要上传到 COS 上对象键
    // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-chengdu.myqcloud.com/mydemo.jpg` 中，对象键为 mydemo.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
    
 
    public static String upload(File image){
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        //file里面填写本地图片的位置 我这里是相对项目的位置，在项目下有src/test/demo.jpg这张图片
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, image);
        cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
        URL url = cosClient.generatePresignedUrl(bucket, key, expiration);
        String result = url.getHost() + url.getPath();
        return result;
    }
    
}
