package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.RequestBody.BaseRequestBody.AdmLoginRequest;
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

    /**
     * 9.11 测试通过
     * @param loginCode
     * @return
     */
    @PostMapping("/api/login/")
    public LoginToken commonUserLogin(@RequestBody LoginCode loginCode) {
        return baseService.login(loginCode.getCode());
    }

    /**
     * 9.12 测试通过
     * @param admLoginRequest
     * @return
     */
    @PostMapping("/api/adm/login")
    public LoginToken admUserLogin(@RequestBody AdmLoginRequest admLoginRequest){
        return baseService.superLogin(
            admLoginRequest.getAccount(), 
            admLoginRequest.getPassword());
    }
}

