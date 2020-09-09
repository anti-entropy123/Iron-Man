package com.mbry.IronMan.Controller;

import com.mbry.IronMan.RequestBody.DetailRequestBody.OrderApplyRequest;
import com.mbry.IronMan.RequestBody.DetailRequestBody.OrderTeamApplyRequest;
import com.mbry.IronMan.RequestBody.DetailRequestBody.ProcessApplyRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.DetailCardResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.GetApplyResponse;
import com.mbry.IronMan.Service.DetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/detail")
public class DetailController {
    @Autowired
    private DetailService detailService;
    
    @GetMapping("/getCardDetail/")
    public DetailCardResponse getCardDetail(@RequestParam String cardId) {
        return detailService.getCardDetail(cardId);
    }
    
    @PostMapping("/orderApply/")
    public DefaultResponse orderApply(@RequestBody OrderApplyRequest orderApplyRequest) {
        return detailService.orderApply(
            orderApplyRequest.getUserId(), 
            orderApplyRequest.getCardId());
    }

    @PostMapping("/orderTeamApply/")
    public DefaultResponse orderTeamApply(@RequestBody OrderTeamApplyRequest orderTeamApplyRequest) {
        return detailService.orderTeamApply(
            orderTeamApplyRequest.getUserId(), orderTeamApplyRequest.getTeamId());
    }
    
    @GetMapping("/getApply/")
    public GetApplyResponse getApply(@RequestParam String cardId) {
        return detailService.getApply(cardId);
    }

    @PostMapping("/processApply/")
    public DefaultResponse processApply(@RequestBody ProcessApplyRequest processApplyRequest) {
        return detailService.processApply(processApplyRequest.getApplyId());
    }

}