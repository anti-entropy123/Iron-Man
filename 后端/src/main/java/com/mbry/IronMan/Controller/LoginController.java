package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.RequestBody.BaseRequestBody.LoginCode;
import com.mbry.IronMan.ResponseBody.BaseResponseBody.LoginToken;
import com.mbry.IronMan.Service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoginController {
    @Autowired
    private BaseService baseService;

    @PostMapping("/api/login/")
    public LoginToken commonUserLogin(@RequestBody LoginCode loginCode) {
        String token = baseService.login(loginCode.getCode());
        return new LoginToken(token, 1, "");
    }
}

