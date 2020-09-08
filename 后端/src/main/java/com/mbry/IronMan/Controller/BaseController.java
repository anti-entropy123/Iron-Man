package com.mbry.IronMan.Controller;

import com.mbry.IronMan.RequestBody.BaseRequestBody.MobileAndCheckCode;
import com.mbry.IronMan.RequestBody.BaseRequestBody.MobileNumber;
import com.mbry.IronMan.ResponseBody.DefaultResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/base")
public class BaseController {
    @PostMapping("/getCheckCode/")
    public DefaultResponse getCheckCode(@RequestBody MobileNumber mobileNumber) {
        return null;
    }

    @PostMapping("/bindMobile/")
    public DefaultResponse bindMobile(@RequestBody MobileAndCheckCode mobileAndCheckCode){
        return null;
    }
}