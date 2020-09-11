package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbry.IronMan.RequestBody.PublishRequestBody.PublishRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.PublishResponseBody.PublishImageResponse;
import com.mbry.IronMan.Service.CardService;
import com.mbry.IronMan.Service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/publish")
public class PublishController {

    @Autowired
    CardService cardService;

    @Autowired
    ImageService imageService;

    /**
     * 9/11 通过测试
     * @param publishRequest
     * @return
     */
    @PostMapping(value="/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse publish(@RequestBody PublishRequest publishRequest) {
        DefaultResponse defaultResponse = new DefaultResponse();
        if (cardService.publishCard(publishRequest)) {
            defaultResponse.setResult(1);
            defaultResponse.setMessage("mbrynb");
        } else {
            defaultResponse.setResult(0);
            defaultResponse.setMessage("server error");
        } 
        return defaultResponse;
    }
    
    /**
     * 9月11日通过测试
     * @param image
     * @return
     */
    
    @PostMapping(value="/image")
    @PreAuthorize("hasRole('common')")
    public PublishImageResponse postMethodName(
        @RequestParam("image") MultipartFile image ) {
        if(image == null){
            return new PublishImageResponse(null, 0, "请设置上传文件");
        }
        return imageService.saveImage(image);
    }

}