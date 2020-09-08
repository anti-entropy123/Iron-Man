package com.mbry.IronMan.Controller;

import com.mbry.IronMan.RequestBody.DetailRequestBody.OrderApplyRequest;
import com.mbry.IronMan.RequestBody.DetailRequestBody.OrderTeamApplyRequest;
import com.mbry.IronMan.RequestBody.DetailRequestBody.ProcessApplyRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.DetailCardResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.GetApplyResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController("/api/detail")
public class DetailController {
    @GetMapping("/getCardDetail/")
    public DetailCardResponse getCardDetail(@RequestParam String cardId) {
        return null;
    }
    
    @PostMapping("/orderApply/")
    public DefaultResponse orderApply(@RequestBody OrderApplyRequest orderApplyRequest) {
        return null;
    }

    @PostMapping("/orderTeamApply/")
    public DefaultResponse orderTeamApply(@RequestBody OrderTeamApplyRequest orderTeamApplyRequest) {
        return null;
    }
    
    @GetMapping("/getApply/")
    public GetApplyResponse getApply(@RequestParam String cardId) {
        return null;
    }

    @PostMapping("/processApply/")
    public DefaultResponse processApply(@RequestBody ProcessApplyRequest processApplyRequest) {
        return null;
    }
    
}