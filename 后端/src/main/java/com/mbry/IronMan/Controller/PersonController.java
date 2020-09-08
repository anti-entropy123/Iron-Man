package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.RequestBody.PersonRequestBody.GetInfoRequest;
import com.mbry.IronMan.RequestBody.PersonRequestBody.UpdateInfoRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController("/api/person")
public class PersonController {
    @GetMapping(value="/info/")
    public GetInfoRequest getInfo(@RequestParam String userId) {
        return null;
    }
    
    @PostMapping(value="/update/")
    public DefaultResponse updateInfo(@RequestBody UpdateInfoRequest updateInfoRequest) {
        return null;
    }
}