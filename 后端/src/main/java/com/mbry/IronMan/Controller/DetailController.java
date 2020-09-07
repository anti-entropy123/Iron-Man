package com.mbry.IronMan.Controller;

import com.mbry.IronMan.ResponseBody.DetailResponseBody.DetailCardResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController("/api/detail")
public class DetailController {
    @GetMapping("/getCardDetail/")
    public DetailCardResponse getMethodName(@RequestParam String cardId) {
        return null;
    }
    
    
}