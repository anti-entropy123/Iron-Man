package com.mbry.IronMan.Utils;

import java.io.File;
import java.io.IOException;
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
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageAPIUtil {

    @Value("${tencent.cos.secretId}")
    private String secretId;

    @Value("${tencent.cos.secretKey}")
    private String secretKey;

    @Value("${tencent.cos.region}")
    private String region;

    @Value("${tencent.cos.bucket}")
    private String bucket;

    private String getKey(){
        return UUID.randomUUID().toString().replace("-", "");
    }
 
    public String upload(File image){
        int index = image.getName().lastIndexOf(".");
        String key = getKey() + image.getName().substring(index);
    	System.out.println(secretId);
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        COSClient cosClient = new COSClient(cred, clientConfig);
 
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, image);
        cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
        URL url = cosClient.generatePresignedUrl(bucket, key , expiration);
        String result = url.getHost() + url.getPath();
        return result;
    }
    
    public File transMultipartFileTofile(MultipartFile image) throws IOException{
        String fileName = image.getOriginalFilename();
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        File file = File.createTempFile(fileName, suffix);
        image.transferTo(file);
        return file;
    }

}
