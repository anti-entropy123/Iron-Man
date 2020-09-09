package com.mbry.IronMan.Controller;

import com.mbry.IronMan.RequestBody.BaseRequestBody.LoginCode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

class test_controller{
    @GetMapping("/")
    String helloworld(@RequestBody LoginCode loginCode){
        return "hello world";
    }
}