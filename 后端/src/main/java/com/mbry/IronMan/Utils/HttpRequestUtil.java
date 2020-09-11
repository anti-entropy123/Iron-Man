package com.mbry.IronMan.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;

@Component
public class HttpRequestUtil {
    private String sendHttpRequest(String url, String param){
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            conn.connect();
            // 获取所有的响应头字段
            // Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
            // for (String key : map.keySet()) {
            // System.out.println(key + "-->" + map.get(key));
            // }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常" + e);
            e.printStackTrace();
        }
        return result;
    }

    public String getOpenIdByCode(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String param = "appid=wx41443c3caff6ac73&secret=a60e5a537d06c742310228e5f9cacc19&js_code=" + code
                + "&grant_type=authorization_code";
        String result = sendHttpRequest(url, param);
        int i = result.lastIndexOf("\n\n");
        return result.substring(i+2);
    }

    public boolean sendSMSMessage(
            String mobile,
            String checkCode){
        String url = "https://api.binstd.com/sms/send";
        String param = "mobile=" + mobile + "&content=【北洋房屋租赁】用户您好。\n您的验证码是: "+ checkCode + "&appkey=yourappkey";
        sendHttpRequest(url, param);
        return true;
    }

}