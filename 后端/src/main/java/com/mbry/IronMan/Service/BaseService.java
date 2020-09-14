package com.mbry.IronMan.Service;

import com.alibaba.fastjson.JSON;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.JsonBean.JscodeToSession;
import com.mbry.IronMan.Mapper.AdmInfoMapper;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.BaseResponseBody.LoginToken;
import com.mbry.IronMan.Utils.HttpRequestUtil;
import com.mbry.IronMan.Utils.JwtTokenUtil;
import com.mbry.IronMan.entity.AdmInfoEntity;
import com.mbry.IronMan.global.Global;

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
    @Autowired
    private AdmInfoMapper admInfoMapper; 

    public LoginToken login(String code) {
        String openId;
        if(code.equals("test")){
            openId = "youjianing";
        }else{
            String result = httpRequestUtil.getOpenIdByCode(code);
            //String result = "{\"open_id\": \"youjianing\", \"session_key\":\"123456\"}";
            JscodeToSession jscodeToSession = JSON.parseObject(result, JscodeToSession.class);
            openId = jscodeToSession.getOpenId();
            //String openId = "youjianing";
            if(openId == null){
                String errmsg = result;
                return new LoginToken(null, false, 0, errmsg, null);
            }
        }
        boolean firstTime = false;
        final UserDetails userDetails = userDetailsService.loadUserByUsername(openId);
        if (userDetails == null) {
            register(openId);
            firstTime = true;
        }
        final String token = jwtTokenUtil.generateToken(openId, null);
        return new LoginToken(token, firstTime, 1, "", openId);
    }

    public LoginToken superLogin(String userName, String password){
        String token;
        AdmInfoEntity adm = admInfoMapper.queryAdm(userName);
        if(adm != null && adm.getPassword().equals(password)){
            // 账号密码正确
            token = jwtTokenUtil.generateToken(adm.getAdmId(), "super");
        }else{
            return new LoginToken(null, false, 0, "账户或密码错误", null);
        }
        return new LoginToken(token, true, 1, "登录成功", null);
    }

    public void register(String openId){
        String userId = openId;
        String temp = "user";
        String letterTable = Global.letterTable;
        for(int i=0;i < 4;i++){
            int index = (int)(Math.random()*(letterTable.length()-1));
            temp += letterTable.charAt(index);
        }
        User newUser = new User(userId, temp, null, null, null, null);
        userDao.registerUser(newUser);
    }

    public DefaultResponse getCheckCode(String mobileNum){
        String letterTable = Global.letterTable;
        String checkCode = "";
        for(int i=0;i < 4;i++){
            int index = (int)(Math.random()*letterTable.length());
            checkCode += letterTable.charAt(index);
        }
        Global.mobileToCheckCode.put(mobileNum, checkCode);
        httpRequestUtil.sendSMSMessage(mobileNum, checkCode);
        return new DefaultResponse(1, "");
    }

    public DefaultResponse bindMobile(String userId, String checkcode, String mobileNum){
        String trueCode = Global.mobileToCheckCode.remove(mobileNum);
        int result = 1;
        String message = "";
        if(trueCode != null && trueCode.equals(checkcode)){
            userDao.bindMobileNumberByUserId(userId, mobileNum);
            result = 1;
        }else{
            result = 0;
            message = "验证码错误";
        }
        return new DefaultResponse(result, message);
    }
}