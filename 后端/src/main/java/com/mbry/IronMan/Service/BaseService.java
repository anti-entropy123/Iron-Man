package com.mbry.IronMan.Service;

import com.alibaba.fastjson.JSON;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.JsonBean.JscodeToSession;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.Utils.HttpRequestUtil;
import com.mbry.IronMan.Utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
@Service
public class BaseService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired 
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    HttpRequestUtil httpRequestUtil;

    private String parseJsonToOpenId(String json) {
        JscodeToSession jscodeToSession = JSON.parseObject(json, JscodeToSession.class);
        return jscodeToSession.getOpenId();
    }

    public String login(String code) {
         String result = httpRequestUtil.getOpenIdByCode(code);
        //String result = "{\"open_id\": \"youjianing\", \"session_key\":\"123456\"}";
         String openId = parseJsonToOpenId(result);
        //String openId = "youjianing";
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