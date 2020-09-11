package com.mbry.IronMan.Controller;

import com.mbry.IronMan.RequestBody.DetailRequestBody.OrderApplyRequest;
import com.mbry.IronMan.RequestBody.DetailRequestBody.OrderTeamApplyRequest;
import com.mbry.IronMan.RequestBody.DetailRequestBody.ProcessApplyRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.DetailCardResponse;
import com.mbry.IronMan.ResponseBody.DetailResponseBody.GetApplyResponse;
import com.mbry.IronMan.Service.DetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    
    /**
     * 9/11 通过测试
     * @param cardId
     * @return
     */
    @GetMapping("/getCardDetail/")
    @PreAuthorize("hasRole('common')")
    public DetailCardResponse getCardDetail(@RequestParam String cardId) {
        return detailService.getCardDetail(cardId);
    }
    
    /**
     * 
     * @param orderApplyRequest
     * @return
     */
    @PostMapping("/orderApply/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse orderApply(@RequestBody OrderApplyRequest orderApplyRequest) {
        return detailService.orderApply(
            orderApplyRequest.getUserId(), 
            orderApplyRequest.getCardId());
    }

    @PostMapping("/orderTeamApply/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse orderTeamApply(@RequestBody OrderTeamApplyRequest orderTeamApplyRequest) {
        return detailService.orderTeamApply(
            orderTeamApplyRequest.getUserId(), orderTeamApplyRequest.getTeamId());
    }
    
    @GetMapping("/getApply/")
    @PreAuthorize("hasRole('common')")
    public GetApplyResponse getApply(@RequestParam String cardId) {
        return detailService.getApply(cardId);
    }

    @PostMapping("/processApply/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse processApply(@RequestBody ProcessApplyRequest processApplyRequest) {
        return detailService.processApply(processApplyRequest.getApplyId());
    }

}