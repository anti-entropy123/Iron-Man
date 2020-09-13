package com.mbry.IronMan.Utils;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.mbry.IronMan.JsonBean.GetAccessTokenResponse;
import com.mbry.IronMan.RequestBody.WxMessageRequestBody.WxMessageRequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WxMessageUtil {

    @Autowired
    private HttpRequestUtil httpRequestUtil;

    private String accssToken; 
    private long lastGetAccessTokenTime = 0;
    long expire = 2 * 60 * 60;

    private boolean isNeedFlushToken(){
        long second = ((new Date().getTime() - lastGetAccessTokenTime)/1000);
        return second > expire;
    }

    private String getAccessToken(){
        if(isNeedFlushToken()){
            String responseStr = httpRequestUtil.getAccessToken();
            GetAccessTokenResponse response = JSON.parseObject(responseStr, GetAccessTokenResponse.class);
            String accessToken = response.getAccessToken();
            int expiresIn = response.getExpiresIn();
            if(accessToken != null){
                this.accssToken = accessToken;
                this.expire = expiresIn;
                this.lastGetAccessTokenTime = new Date().getTime();
            }
        }
        return this.accssToken;
    }

    public void sendMessage(WxMessageRequestBody.Data data, String touser) {
        getAccessToken();
        // String templateId = null;
        // String page = null;
        // String fromId = null;
        // WxMessageRequestBody wxMessageRequestBody = new WxMessageRequestBody(
        //     touser,
        //     templateId,
        //     page,
        //     fromId,
        //     data
        // );
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_JSON);
        // String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + getAccessToken();
        // HttpEntity<WxMessageRequestBody> entity = new HttpEntity<WxMessageRequestBody>(wxMessageRequestBody, headers);
        // RestTemplate restTemplate = new RestTemplate();
        // ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        // String user = responseEntity.getBody();
        /*
        {
            "errcode": 0,
            "errmsg": "ok",
        }
        40037	template_id不正确
        41028	form_id不正确，或者过期
        41029	form_id已被使用
        41030	page不正确
        */
    }

}