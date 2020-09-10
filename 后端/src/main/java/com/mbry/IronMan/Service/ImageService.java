package com.mbry.IronMan.Service;

import java.io.File;

import com.mbry.IronMan.Utils.ImageAPIUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    
    @Autowired
    ImageAPIUtil imageAPIUtil;

    public String saveImage(File image) {
        return imageAPIUtil.upload(image);
    }
}
