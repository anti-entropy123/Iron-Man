package com.mbry.IronMan.Service;

import java.io.File;
import java.io.IOException;

import com.mbry.IronMan.ResponseBody.PublishResponseBody.PublishImageResponse;
import com.mbry.IronMan.Utils.ImageAPIUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    
    @Autowired
    ImageAPIUtil imageAPIUtil;

    public PublishImageResponse saveImage(MultipartFile image) {
        PublishImageResponse response;
        try{
            File file = imageAPIUtil.transMultipartFileTofile(image);
            String url = imageAPIUtil.upload(file);
            if (file.exists()) {
                file.delete();
            }
            response = new PublishImageResponse(url, 1, "");
        }catch(IOException e){
            response = new PublishImageResponse("", 0, "upload error");
        }catch(Exception e){
            response = new PublishImageResponse("", 0, "server error");
        }
        return response;
    }
}
