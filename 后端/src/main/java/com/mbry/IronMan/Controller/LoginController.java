package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.RequestBody.BaseRequestBody.LoginCode;
import com.mbry.IronMan.ResponseBody.BaseResponseBody.LoginToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoginController {
    @PostMapping("/api/login/")
    public LoginToken commonUserLogin(@RequestBody LoginCode loginCode) {
        return null;
    }
        
}

