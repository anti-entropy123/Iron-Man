package com.mbry.IronMan.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.Utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.net.URLConnection;
@Service
public class BaseService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired 
    JwtTokenUtil jwtTokenUtil;

    private static String getOpenIdByCode(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String param = "appid=wx41443c3caff6ac73&secret=a60e5a537d06c742310228e5f9cacc19&js_code=" + code
                + "&grant_type=authorization_code";
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

    private String parseJsonToOpenId(String json) {
        String openId = "";
        String errmsg = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            errmsg = rootNode.path("errmsg").asText();
            openId = rootNode.path("openid").asText();
        } catch (IOException e) {
            System.out.println("json 格式不对");
            openId = errmsg;
        }
        return openId;
    }

    public String login(String code) {
        String result = getOpenIdByCode(code);
        String openId = parseJsonToOpenId(result);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(openId);
        if (userDetails == null) {
            register(openId);
        }
        final String token = jwtTokenUtil.generateToken(openId);
        return token;
    }

    public void register(String openId){
        String userId = openId;
        User newUser = new User(userId, null, null, null, null, null);
        userDao.registerUser(newUser);
    }

    public DefaultResponse getCheckCode(String mobileNum){
        // TODO 调短信接口
        return new DefaultResponse(1, "");
    }

    public DefaultResponse bindMobile(String userId, String checkcode, String mobileNum){
        String trueCode = "";
        int result = 1;
        String message = "";
        if(checkcode.equals(trueCode)){
            userDao.bindMobileNumberByUserId(userId, mobileNum);
            result = 1;
        }else{
            result = 0;
            message = "验证码错误";
        }
        return new DefaultResponse(result, message);
    }
}