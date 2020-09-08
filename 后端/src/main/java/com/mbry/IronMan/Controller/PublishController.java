package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbry.IronMan.RequestBody.PublishRequestBody.PublishRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.PublishResponseBody.PublishImageResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController("/api/publish")
public class PublishController {
    @PostMapping(value="/")
    public DefaultResponse publish(@RequestBody PublishRequest publishRequest) {
        return null;
    }
    
    @PostMapping(value="/image")
    public PublishImageResponse postMethodName(
        @RequestParam("image") MultipartFile image ) {
        return null;
    }
}