package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbry.IronMan.RequestBody.PublishRequestBody.PublishRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.PublishResponseBody.PublishImageResponse;
import com.mbry.IronMan.Service.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/publish")
public class PublishController {

    @Autowired
    CardService cardService;

    /**
     * 
     * @param publishRequest
     * @return
     */
    @PostMapping(value="/")
    public DefaultResponse publish(@RequestBody PublishRequest publishRequest) {
        DefaultResponse defaultResponse = new DefaultResponse();
        if (cardService.publishCard(publishRequest)) {
            defaultResponse.setResult(1);
            defaultResponse.setMessage("mbrynb");
        } else {
            defaultResponse.setResult(0);
            defaultResponse.setMessage("server error");
        } 
        return null;
    }
    
    /**
     * 待实现
     * @param image
     * @return
     */
    
    @PostMapping(value="/image")
    public PublishImageResponse postMethodName(
        @RequestParam("image") MultipartFile image ) {
        return null;
    }
}