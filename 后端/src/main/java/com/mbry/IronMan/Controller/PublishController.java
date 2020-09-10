package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import com.mbry.IronMan.RequestBody.PublishRequestBody.PublishRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.PublishResponseBody.PublishImageResponse;
import com.mbry.IronMan.Service.CardService;
import com.mbry.IronMan.Service.ImageService;

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

    @Autowired
    ImageService imageService;

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
        return defaultResponse;
    }
    
    /**
     * 待实现
     * @param image
     * @return
     */
    
    @PostMapping(value="/image")
    public PublishImageResponse postMethodName(
        @RequestParam("image") MultipartFile image ) {
        
        PublishImageResponse publishImageResponse = new PublishImageResponse();
        String fileName = image.getOriginalFilename();
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        try {
            File  file = File.createTempFile(fileName, suffix);
            try {
                image.transferTo(file);
                publishImageResponse.setUrl(imageService.saveImage(file));
                publishImageResponse.setResult(1);
                publishImageResponse.setMessage("mbrynb");
                if (file.exists()) {
                    file.delete();
                }
                return publishImageResponse;
            } catch (Exception e) {
                e.printStackTrace();
                publishImageResponse.setResult(0);
                publishImageResponse.setMessage("server error");
                return publishImageResponse;
            }
        } catch(Exception e) {
            e.printStackTrace();
            publishImageResponse.setResult(0);
            publishImageResponse.setMessage("server error");
            return publishImageResponse;
        } 
        
    }

}