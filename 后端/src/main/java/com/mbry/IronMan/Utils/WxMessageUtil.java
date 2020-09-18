package com.mbry.IronMan.Utils;

// import java.util.Date;

// import com.alibaba.fastjson.JSON;
// import com.mbry.IronMan.BusinessObject.other.JsonBean.GetAccessTokenResponse;
import com.mbry.IronMan.RequestBody.WxMessageRequestBody.WxMessageRequestBody;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
// import org.springframework.web.client.RestTemplate;

@Component
public class WxMessageUtil {

    // @Autowired
    // private HttpRequestUtil httpRequestUtil;

    // private String accssToken; 
    // private long lastGetAccessTokenTime = 0;
    // long expire = 2 * 60 * 60;

    // private Boolean isNeedFlushToken(){
    //     long second = ((new Date().getTime() - lastGetAccessTokenTime)/1000);
    //     return second > expire;
    // }

    // private String getAccessToken(){
    //     // if(isNeedFlushToken()){
    //     //     String responseStr = httpRequestUtil.getAccessToken();
    //     //     GetAccessTokenResponse response = JSON.parseObject(responseStr, GetAccessTokenResponse.class);
    //     //     String accessToken = response.getAccessToken();
    //     //     int expiresIn = response.getExpiresIn();
    //     //     if(accessToken != null){
    //     //         this.accssToken = accessToken;
    //     //         this.expire = expiresIn;
    //     //         this.lastGetAccessTokenTime = new Date().getTime();
    //     //     }
    //     // }
    //     return this.accssToken;
    // }

    public void sendMessage(WxMessageRequestBody.Data data, String touser) {
        // getAccessToken();
        // String templateId = "73JjfMviVMAqVmYLWRL18iALfyTRfnH8zus0x4ydvDU";
        // String page = null;
        // String formId = "null";
        // WxMessageRequestBody wxMessageRequestBody = new WxMessageRequestBody(
        //     touser,
        //     templateId,
        //     page,
        //     formId,
        //     data
        // );
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_JSON);
        // String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken();
        // HttpEntity<WxMessageRequestBody> entity = new HttpEntity<WxMessageRequestBody>(wxMessageRequestBody, headers);
        // RestTemplate restTemplate = new RestTemplate();
        // ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        // String user = responseEntity.getBody();
        // System.out.println(user);
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