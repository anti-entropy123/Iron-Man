package com.mbry.IronMan.demo;

import com.mbry.IronMan.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class HttpPostDemo {
    
    @Test
    void test() {
        RestTemplate restTemplate = new RestTemplate();
 
        //创建请求头
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://188.131.227.20:8000/";
 
        User student = new User("sansan",10,"bunanbunv");
        HttpEntity<User> entity = new HttpEntity<User>(student, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        String user = responseEntity.getBody();//{"msg":"调用成功！","code":1}
        System.out.println(user);


    }
}
