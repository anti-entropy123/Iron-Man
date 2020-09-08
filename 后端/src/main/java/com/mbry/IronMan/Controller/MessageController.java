package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.ResponseBody.MessageResponseBody.GetAllMessageResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/message")
public class MessageController{
    @GetMapping(value="/getAll/")
    public GetAllMessageResponse getMethodName(@RequestParam String userId) {
        return null;
    }
}