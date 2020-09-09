package com.mbry.IronMan.Controller;

import com.mbry.IronMan.RequestBody.BaseRequestBody.MobileAndCheckCode;
import com.mbry.IronMan.RequestBody.BaseRequestBody.MobileNumber;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.Service.BaseService;
import com.mbry.IronMan.Utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/base")
public class BaseController {
    @Autowired
    private BaseService baseService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("jwt.tokenHead")
    private String tokenHead;

    @PostMapping("/getCheckCode/")
    public DefaultResponse getCheckCode(
            @RequestBody MobileNumber mobileNumber) {
        return baseService.getCheckCode(mobileNumber.getNumber());
    }

    @PostMapping("/bindMobile/")
    public DefaultResponse bindMobile(
            @RequestBody MobileAndCheckCode mobileAndCheckCode,
            @RequestHeader HttpHeaders headers){

        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        return baseService.bindMobile(
            jwtTokenUtil.getOpenIdFromToken(token), 
            mobileAndCheckCode.getCheckcode(), 
            mobileAndCheckCode.getMobileNumber());
    }
}