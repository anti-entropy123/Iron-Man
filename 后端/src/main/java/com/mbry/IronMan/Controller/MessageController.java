package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.ResponseBody.MessageResponseBody.GetAllMessageResponse;
import com.mbry.IronMan.Service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/message")
public class MessageController{

    @Autowired
    MessageService messageService;

    /**
     * 返回指定user的通知消息
     * type = 0 user的card被人申请 返回cardId userId（申请人） applyId
     * type = 1 user的队伍有人申请加入 返回cardId userId applyId
     * type = 2 user的入队申请通过了 返回cardId 下同
     * type = 3 user的队伍有人退出
     * type = 4 user的队伍被解散
     * type = 5 user的申请被处理
     * @param userId
     * @return
     * 9.12 通过测试
     */
    @GetMapping(value="/getAll/")
    @PreAuthorize("hasRole('common')")
    public GetAllMessageResponse getMethodName(@RequestParam String userId) {
        GetAllMessageResponse response = new GetAllMessageResponse();
        GetAllMessageResponse.Data data = response.new Data();
        try {
            GetAllMessageResponse.Data.Message[] messages = messageService.getLogsByAimUser(userId);
            data.setMessages(messages);
            response.setData(data);
            response.setResult(1);
            response.setMessage("mbrynb");
        } catch(Exception e) {
            e.printStackTrace();
            response.setResult(0);
            response.setMessage("server error");
        }
        return response;
    }
    
}