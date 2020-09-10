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
import org.springframework.stereotype.Component;

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

    private final String key = UUID.randomUUID().toString().replace("-", "");

    public String upload(final File image) {
        System.out.println(secretId);
        final COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        final ClientConfig clientConfig = new ClientConfig(new Region(region));
        final COSClient cosClient = new COSClient(cred, clientConfig);

        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, image);
        cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        final Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
        final URL url = cosClient.generatePresignedUrl(bucket, key, expiration);
        final String result = url.getHost() + url.getPath();
        return result;
    }
    
}
